/**
 * Copyright 2013-2021 the original author or authors from the JHipster project.
 *
 * This file is part of the JHipster project, see https://www.jhipster.tech/
 * for more information.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
const chalk = require('chalk');
const EntityGenerator = require('generator-jhipster/generators/entity');

const { prepareEntityForTemplates, prepareEntityPrimaryKeyForTemplates } = require('../../utils/entity');
const { prepareFieldForTemplates } = require('../../utils/field');
const { prepareRelationshipForTemplates } = require('../../utils/relationship');

module.exports = class extends EntityGenerator {
  constructor(args, opts) {
    super(args, { fromBlueprint: true, ...opts }); // fromBlueprint variable is important

    if (!this.jhipsterContext) {
      this.error(`This is a JHipster blueprint and should be used only like ${chalk.yellow('jhipster --blueprint composite-key-server')}`);
    }
  }

  get initializing() {
    return this._initializing();
  }

  get prompting() {
    return this._prompting();
  }

  get configuring() {
    return this._configuring();
  }

  get composing() {
    return this._composing();
  }

  get loading() {
    return this._loading();
  }

  get preparingFields() {
    return {
      ...this._preparingFields(),
      ...{
        // If primaryKey doesn't exist, create it.
        preparePrimaryKey() {
          const entity = this.context;
          if (!entity.embedded && !entity.primaryKey) {
            prepareEntityPrimaryKeyForTemplates(entity, this);
          }
        },

        prepareFieldsForTemplates() {
          const entity = this.context;

          this.context.fields.forEach(field => {
            prepareFieldForTemplates(entity, field, this);
          });
        },
      },
    };
  }

  get preparing() {
    return { ...this._preparing(), ...{
        prepareEntityForTemplates() {
            const entity = this.context;
            prepareEntityForTemplates(entity, this);
        },
      }
    };
  }

  get preparingRelationships() {
    return {
      ...this._preparingRelationships(),
      ...{
        prepareRelationshipsForTemplates() {
          this.context.relationships.forEach(relationship => {
            prepareRelationshipForTemplates(this.context, relationship, this);

            // Load in-memory data for root
            if (relationship.relationshipType === 'many-to-many' && relationship.ownerSide) {
              this.context.fieldsContainOwnerManyToMany = true;
            } else if (relationship.relationshipType === 'one-to-one' && !relationship.ownerSide) {
              this.context.fieldsContainNoOwnerOneToOne = true;
            } else if (relationship.relationshipType === 'one-to-one' && relationship.ownerSide) {
              this.context.fieldsContainOwnerOneToOne = true;
            } else if (relationship.relationshipType === 'one-to-many') {
              this.context.fieldsContainOneToMany = true;
            } else if (relationship.relationshipType === 'many-to-one') {
              this.context.fieldsContainManyToOne = true;
            }
            if (relationship.otherEntityIsEmbedded) {
              this.context.fieldsContainEmbedded = true;
            }
            if (relationship.relationshipValidate) {
              this.context.validation = true;
            }

            const entityType = relationship.otherEntityNameCapitalized;
            if (!this.context.differentTypes.includes(entityType)) {
              this.context.differentTypes.push(entityType);
            }
            if (!this.context.differentRelationships[entityType]) {
              this.context.differentRelationships[entityType] = [];
            }
            this.context.differentRelationships[entityType].push(relationship);
          });
        },
      }
    };
  }

  get default() {
    return this._default();
  }

  get writing() {
    return this._writing();
  }

  get install() {
    return this._install();
  }

  get end() {
    return this._end();
  }
}
