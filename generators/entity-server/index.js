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
/* eslint-disable consistent-return */
const chalk = require('chalk');
const EntityServerGenerator = require('generator-jhipster/generators/entity-server');

const writeFiles = require('./files').writeFiles;

module.exports = class extends EntityServerGenerator {
    constructor(args, opts) {
        super(args, { fromBlueprint: true, ...opts }); // fromBlueprint variable is important

        if (!this.jhipsterContext) {
            this.error(
                `This is a JHipster blueprint and should be used only like ${chalk.yellow('jhipster --blueprint composite-key-server')}`
            );
        }
    }

    get initializing() {
        return super._initializing();
    }

    get preparingFields() {
        return super._preparingFields();
    }

    get default() {
        return super._default();
    }

    get writing() {
        const phaseFromJHipster = super._writing();
        const customPhaseSteps = {
            ...writeFiles()
        };
        return Object.assign(phaseFromJHipster, customPhaseSteps);
    }

    get postWriting() {
        return super._postWriting();
    }

    // remove when merged
    _getPrimaryKeyValue(primaryKey, databaseType = this.jhipsterConfig.databaseType, defaultValue = 1) {
      if (typeof primaryKey === 'object' && primaryKey.composite) {
        return `new ${primaryKey.type}(${primaryKey.references
          .map(ref => this._getPrimaryKeyValue(ref.type, databaseType, defaultValue))
          .join(', ')})`;
      }
      const primaryKeyType = typeof primaryKey === 'string' ? primaryKey : primaryKey.type;
      if (primaryKeyType === 'String') {
        if (databaseType === 'sql' && defaultValue === 0) {
          return 'UUID.randomUUID().toString()';
        }
        return `"id${defaultValue}"`;
      }
      if (primaryKeyType === 'UUID') {
        return 'UUID.randomUUID()';
      }
      if (primaryKeyType === 'Integer') {
        return `${defaultValue}`;
      }
      return `${defaultValue}L`;
    }
};
