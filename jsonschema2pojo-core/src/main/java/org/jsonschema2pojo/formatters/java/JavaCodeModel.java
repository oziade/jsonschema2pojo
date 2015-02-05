/**
 * Copyright © 2010-2014 Nokia
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

package org.jsonschema2pojo.formatters.java;

import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JCodeModel;
import org.jsonschema2pojo.formatters.CodeModel;
import org.jsonschema2pojo.formatters.SupportedLanguage;

import java.io.IOException;

/**
 * Created by Olivier Ziadé on 04/02/15.
 */
public class JavaCodeModel extends CodeModel {
    public JavaCodeModel(JCodeModel codeModel, SupportedLanguage language) {
        super(codeModel, language);
    }

    public void build(CodeWriter sourceWriter, CodeWriter resourcesWriter) throws IOException {
        codeModel.build(sourceWriter, resourcesWriter);
    }
}
