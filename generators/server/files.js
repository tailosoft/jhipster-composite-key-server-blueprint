/**
 * Copyright 2013-2019 the original author or authors from the JHipster project.
 *
 * This file is part of the JHipster project, see https://www.jhipster.tech/
 * for more information.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
const constants = require('generator-jhipster/generators/generator-constants');

/* Constants use throughout */
const SERVER_MAIN_SRC_DIR = constants.SERVER_MAIN_SRC_DIR;
const SERVER_TEST_SRC_DIR = constants.SERVER_TEST_SRC_DIR;

/**
 * The default is to use a file path string. It implies use of the template method.
 * For any other config an object { file:.., method:.., template:.. } can be used
 */
const serverFiles = {
    server: [
        {
            path: SERVER_MAIN_SRC_DIR,
            templates: [
                {
                    file: 'package/config/MatrixVariableConfiguration.java',
                    renameTo: generator => `${generator.packageFolder}/config/MatrixVariableConfiguration.java`
                }
            ]
        }
    ]
};

module.exports = {
    writeFiles
};

function writeFiles() {
    return {
        fixTestUtil() {
            if (this.skipServer) return;
            const fileName = `${SERVER_TEST_SRC_DIR}${this.packageFolder}/web/rest/TestUtil.java`;
            const result = this.fs
                .read(fileName)
                .replace(
                    'import com.fasterxml.jackson.annotation.JsonInclude;',
                    `import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;`
                )
                .replace(
                    'mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);',
                    `mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        mapper.configure(MapperFeature.USE_ANNOTATIONS, false); // ignores JsonIgnoreProperties for nested composite keys`
                );
            this.fs.write(fileName, result);
        },
        writeExtraServerFiles() {
            if (this.skipServer) return;

            // write server side files
            this.writeFilesToDisk(serverFiles, this, false, '.');
        }
    };
}
