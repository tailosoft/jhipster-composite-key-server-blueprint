/* eslint-disable consistent-return */
const chalk = require('chalk');
const _ = require('lodash');
const path = require('path');
const EntityServerGenerator = require('generator-jhipster/generators/entity-server');
const writeFiles = require('./files').writeFiles;

const JHIPSTER_CONFIG_DIR = '.jhipster';

const defaultIdField = {
    name: 'id',
    fieldName: 'id',
    fieldType: 'Long',
    fieldNameAsDatabaseColumn: 'id',
    fieldNameUnderscored: 'id',
    fieldInJavaBeanMethod: 'Id',
    fieldNameHumanized: 'ID',
    fieldValidate: false,
    fieldValidateRules: [],
    options: { id: true }
};

module.exports = class extends EntityServerGenerator {
    constructor(args, opts) {
        super(args, { fromBlueprint: true, ...opts }); // fromBlueprint variable is important

        if (!this.jhipsterContext) {
            this.error(`This is a JHipster blueprint and should be used only like ${chalk.yellow('jhipster --blueprint helloworld')}`);
        }
    }

    get initializing() {
        return super._initializing();
    }

    get writing() {
        /**
         * Any method beginning with _ can be reused from the superclass `EntityServerGenerator`
         *
         * There are multiple ways to customize a phase from JHipster.
         *
         * 1. Let JHipster handle a phase, blueprint doesnt override anything.
         * ```
         *      return super._writing();
         * ```
         *
         * 2. Override the entire phase, this is when the blueprint takes control of a phase
         * ```
         *      return {
         *          myCustomWritePhaseStep() {
         *              // Do all your stuff here
         *          },
         *          myAnotherCustomWritePhaseStep(){
         *              // Do all your stuff here
         *          }
         *      };
         * ```
         *
         * 3. Partially override a phase, this is when the blueprint gets the phase from JHipster and customizes it.
         * ```
         *      const phaseFromJHipster = super._writing();
         *      const myCustomPhaseSteps = {
         *          writeClientFiles() {
         *              // override the writeClientFiles method from the _writing phase of JHipster
         *          },
         *          myCustomInitPhaseStep() {
         *              // Do all your stuff here
         *          },
         *      }
         *      return Object.assign(phaseFromJHipster, myCustomPhaseSteps);
         * ```
         */
        const prePhaseSteps = {
            // making sure name is unique to not override any step
            compositeKeyServerConfiguration() {
                const context = this;
                if (!this.allContexts) {
                    const userContext = {
                        name: 'User',
                        fields: [],
                        relationships: []
                    };
                    this._computePkData(userContext)
                    this.allContexts = {
                        user: userContext
                    };
                }
                this._computePkData(context);
                // compute relationships pkData
                this.relationships.forEach(r => {
                    this._populateRelationshipPkData(r);
                });
                if (context.pkData.length === 1) {
                    context.pkType = context.pkData[0].type;
                    context.pkName = context.pkData[0].name;
                } else {
                    context.pkType = `${context.entityClass}Id`;
                    context.pkName = 'id';
                }
                context.pkData.forEach(pk => {
                    pk.nameCapitalized = _.upperFirst(pk.name);
                    pk.columnName = _.snakeCase(pk.name);
                    pk.getter = (pk.type === 'Boolean' ? 'is' : 'get') + pk.nameCapitalized;
                    pk.setter = `set${pk.nameCapitalized}`;
                });
            },

            compositeKeyServerLoadFieldsInrelationship() {
                this.relationships.forEach(r => {
                    const otherContext = this._getEntityJson(r.otherEntityName);
                    r.fields = otherContext.fields;
                    if (r.pkData.length === 1 && r.pkData[0].name === 'id' && r.pkData[0].type === 'Long') {
                        r.fields.unshift(defaultIdField);
                    }
                    r.relationships = otherContext.relationships;
                });
            }
        };
        const phaseFromJHipster = super._writing();
        const customPhaseSteps = {
            writeServerFiles() {
                // override the writeServerFiles method from the _writing phase of JHipster
                writeFiles().writeServerFiles.call(this);
            },

            writeEnumFiles() {
                // override the writeEnumFiles method from the _writing phase of JHipster
                writeFiles().writeEnumFiles.call(this);
            }
        };
        return Object.assign(prePhaseSteps, phaseFromJHipster, customPhaseSteps);
    }

    _loadRelationshipPkData(entityName) {
        const context = this._getEntityJson(entityName);
        this._computePkData(context);
        return context.pkData;
    }

    _computePkData(context) {
        if (context.pkData) {
            return context.pkData;
        }
        if (!context.fields.some(x => x.options && x.options.id) && !context.relationships.some(r => this._checkRelationshipPartOfId(r))) {
            context.fields.unshift(defaultIdField);
        }
        context.pkData = [];
        for (let i = 0; i < context.fields.length; i++) {
            const field = context.fields[i];
            if (field.options && field.options.id) {
                field.partOfId = true;
                context.pkData.push({
                    // To load field like javadoc, validation... for DTO
                    ...field,
                    name: field.fieldName,
                    type: field.fieldType,
                    entityName: context.name,
                    field
                });
            }
        }

        for (let i = 0; i < context.relationships.length; i++) {
            const relationship = context.relationships[i];
            // if relationship partOfId we initialize it's pkData first since it's need to fill own pkData
            // else we fill pkData before loading, this avoids recursion problems
            if (this._checkRelationshipPartOfId(relationship)) {
                this._populateRelationshipPkData(relationship);
                relationship.partOfId = true;
                context.pkData.push(
                    ...relationship.pkData.map(pk => {
                        // there might be a more complicate logic required here, that takes into account, both if the relationship is required, and if the fields of the if of the relationship are
                        // but for now we just set it to required if the relationship is required
                        // set as an array and use fieldValidateRules to have the same code in DTO both if pk is a field in the current entity or comes from a relationship
                        const name = relationship.relationshipName + _.upperFirst(pk.name);
                        return {
                            ...pk,
                            name,
                            nameCapitalized: _.upperFirst(name),
                            relationship
                        };
                    })
                );
            }
        }
    }

    _checkRelationshipPartOfId(relationship) {
        return relationship.options && relationship.options.id && relationship.relationshipType === 'many-to-one';
    }

    _getEntityJson(file) {
        let entityJson = this.allContexts[file];
        if (entityJson) {
            return entityJson;
        }
        try {
            if (this.microservicePath) {
                entityJson = this.fs.readJSON(path.join(this.microservicePath, JHIPSTER_CONFIG_DIR, `${_.upperFirst(file)}.json`));
            } else {
                entityJson = this.fs.readJSON(path.join(JHIPSTER_CONFIG_DIR, `${_.upperFirst(file)}.json`));
            }
        } catch (err) {
            this.log(chalk.red(`The JHipster entity configuration file could not be read for file ${file}!`) + err);
            this.debug('Error:', err);
        }
        this.allContexts[file] = entityJson;
        return entityJson;
    }

    _populateRelationshipPkData(relationship) {
        if (!relationship.pkData) {
            // cloning each pk in pkData to be able to modify it (add relationshipName prefix...)
            relationship.pkData = this._loadRelationshipPkData(relationship.otherEntityName).map(pk => ({ ...pk }));
            if (!relationship.otherEntityField || relationship.otherEntityField === 'id') {
                relationship.otherEntityField = relationship.pkData[0].name;
            }
            relationship.pkData.forEach(pk => {
                pk.nameCapitalized = _.upperFirst(pk.name);
                pk.fieldValidate = relationship.relationshipValidateRules === 'required';
                pk.fieldValidateRules = pk.fieldValidate ? ['required'] : [];
                pk.otherEntityNameCapitalized = relationship.otherEntityNameCapitalized;
                pk.formName = relationship.relationshipName + (pk.formName ? _.upperFirst(pk.formName) : '');
                // if two ids are created using fields we might need to check here if so this is done only once, I personally don't see any real use case for this
                if (!pk.otherEntityField) {
                    pk.otherEntityField = relationship.otherEntityField;
                }
                if (!pk.otherEntityFieldDTOSource) {
                    pk.otherEntityFieldDTOSource = pk.otherEntityField;
                }
                pk.otherEntityFieldDTOSource = `${relationship.relationshipName}.${pk.otherEntityFieldDTOSource}`;
                if (pk.relationship && _.upperFirst(pk.relationship.otherEntityName) === this.name) {
                    pk.selfReference = true;
                }
            });
        }
    }
};
