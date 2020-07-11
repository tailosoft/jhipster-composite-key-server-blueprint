const path = require('path');
const fse = require('fs-extra');
const fs = require('fs');
const assert = require('yeoman-assert');
const helpers = require('yeoman-test');
const rimraf = require('rimraf');

const importJdl = require('generator-jhipster/cli/import-jdl');
const walker = require('./path-walker');

const noopFork = () => ({
    on(code, cb) {
        cb(0);
    }
});

let testDir = '/tmp/composite-key-blueprint-test';
describe('import-jdl of composite key server JHipster blueprint', () => {
    describe('Sample test', () => {
        before(done => {
            rimraf.sync(testDir);
            helpers
                .run('generator-jhipster/generators/app')
                .inDir(testDir, dir => {
                    testDir = dir;
                    fse.copySync(path.join(__dirname, '../test/templates/composite-key-blueprint'), dir);
                    importJdl(['jhipster.jh'], { skipInstall: true, noInsight: true, interactive: false, 'skip-git': false }, {}, noopFork);
                    // even though we use import-jdl we keep the json files to avoid changing the timestamps and therefore the liquibase files
                })
                .withOptions({
                    'from-cli': true,
                    skipInstall: true,
                    blueprint: 'composite-key-server',
                    skipChecks: true,
                    // skipClient: true,
                    withEntities: true
                })
                .withGenerators([
                    [
                        require('../generators/server/index.js'), // eslint-disable-line global-require
                        'jhipster-composite-key-server:server',
                        path.join(__dirname, '../generators/server/index.js')
                    ],
                    [
                        require('../generators/entity-server/index.js'), // eslint-disable-line global-require
                        'jhipster-composite-key-server:entity-server',
                        path.join(__dirname, '../generators/entity-server/index.js')
                    ]
                ])
                .withPrompts({
                    fieldAdd: false,
                    relationshipAdd: false,
                    dto: 'yes',
                    service: 'serviceClass',
                    pagination: 'paginate'
                })
                .on('end', done);
        });

        it('it works', () => {
            // eslint-disable-next-line no-console
            console.log(testDir);
            const basePath = path.join(__dirname, './samples/composite-key-blueprint');
            const javaPackage = `${basePath}/src/main/java/com/mycompany/myapp`;
            const filesToTest = [
                // `${basePath}/src/main/resources/config/liquibase/changelog/20190718222328_added_entity_constraints_EmployeeSkill.xml`,
                `${javaPackage}/service/dto`,
                `${basePath}/src/main/resources/config/liquibase`,
                `${javaPackage}/domain`,
                `${javaPackage}/web/rest`,
                `${javaPackage}/repository`,
                `${javaPackage}/service`,
                `${basePath}/src/test/java/com/mycompany/myapp/service/dto`,
                `${basePath}/src/test/java/com/mycompany/myapp/service/mapper`,
                `${basePath}/src/test/java/com/mycompany/myapp/domain`,
                `${basePath}/src/test/java/com/mycompany/myapp/web/rest`,
                `${javaPackage}/config/MatrixVariableConfiguration.java`
            ];
            filesToTest.forEach(file =>
                walker.walk(file).forEach(f => {
                    assert.file(path.join(testDir, f.substring(basePath.length)));
                    assert.textEqual(
                        fs.readFileSync(path.join(testDir, f.substring(basePath.length)), 'UTF-8'),
                        fs.readFileSync(path.join(basePath, f.substring(basePath.length)), 'UTF-8')
                    );
                })
            );
        });
    });
});
