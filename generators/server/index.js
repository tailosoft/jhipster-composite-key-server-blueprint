/* eslint-disable consistent-return */
const chalk = require('chalk');
const ServerGenerator = require('generator-jhipster/generators/server');
const writeFiles = require('./files').writeFiles;

module.exports = class extends ServerGenerator {
    constructor(args, opts) {
        super(args, Object.assign({ fromBlueprint: true }, opts)); // fromBlueprint variable is important

        const jhContext = (this.jhipsterContext = opts.jhipsterContext);

        if (!jhContext) {
            this.error(
                `This is a JHipster blueprint and should be used only like ${chalk.yellow('jhipster --blueprint composite-key-server')}`
            );
        }

        this.configOptions = jhContext.configOptions || {};
        if (jhContext.databaseType === 'cassandra') {
            this.error("cassandra doesn't support composite keys");
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

    get default() {
        return this._default();
    }

    get writing() {
        const phaseFromJHipster = super._writing();
        const customPhaseSteps = {
            writeServerFiles() {
                // override the writeServerFiles method from the _writing phase of JHipster
                writeFiles().writeServerFiles.call(this);
            }
        };
        return Object.assign(phaseFromJHipster, customPhaseSteps);
    }

    get install() {
        return this._install();
    }

    get end() {
        return this._end();
    }
};
