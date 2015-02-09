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

package org.jsonschema2pojo.formatters.swift.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Preconditions;
import com.sun.codemodel.ClassType;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import org.jsonschema2pojo.exception.GenerationException;
import org.jsonschema2pojo.formatters.swift.SwiftCodeModel;
import org.jsonschema2pojo.rules.EnumRule;
import org.jsonschema2pojo.util.StringBuilderUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Swift class representation
 * Created by Olivier Ziadé on 05/02/15.
 */
public class SwiftClass extends SwiftDeclaration {

    public static final String DECLARATION_NAME = "class";
    private final JDefinedClass definedClass;

    /**
     * Constructor
     * @param definedClass Java class
     */
    public SwiftClass(final JDefinedClass definedClass) {
        Preconditions.checkNotNull(definedClass);

        this.definedClass = definedClass;
        this.name = definedClass.name();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String declare() throws GenerationException {
        final String className = definedClass.name();
        StringBuilder classCode = new StringBuilder();

        // Imports
        SwiftImport foundation = new SwiftImport("Foundation", this);
        StringBuilderUtil.appendln(classCode, foundation.toSourceCode());
        SwiftImport mapper = new SwiftImport("ObjectMapper", this);
        StringBuilderUtil.appendln(classCode, mapper.toSourceCode());
        StringBuilderUtil.appendln(classCode, "");

        if (ClassType.ENUM != definedClass.getClassType()) {
            // Class
            StringBuilderUtil.appendln(classCode, DECLARATION_NAME + " " + className + ": Mappable {");

            // Fields
            Map<String, JFieldVar> fields = definedClass.fields();
            SwiftMapFunction mapFunction = new SwiftMapFunction("map", this);
            for (Map.Entry<String, JFieldVar> entry : fields.entrySet()) {
                final String key = entry.getKey();
                if (!"additionalProperties".equals(key)) {
                    SwiftField field = new SwiftField(key, SwiftCodeModel.parseType(entry.getValue().type()), this);
                    StringBuilderUtil.appendln(classCode, field.toSourceCode());
                    mapFunction.addStatement(field);
                }
            }
            StringBuilderUtil.appendln(classCode, "");

            // Constructor
            SwiftConstructor defaultConstructor = new SwiftConstructor(this);
            StringBuilderUtil.appendln(classCode, defaultConstructor.toSourceCode());

            // Mapper
            StringBuilderUtil.appendln(classCode, mapFunction.toSourceCode());
            StringBuilderUtil.appendln(classCode, "}");
        } else {
            // Enum
            JsonNode enumValueNode = EnumRule.enumToValues.get(className);
            List<String> enumValues = new ArrayList<>();

            for (Iterator<JsonNode> values = enumValueNode.elements(); values.hasNext(); ) {
                JsonNode value = values.next();
                if (!value.isNull()) {
                    enumValues.add(value.asText());
                }
            }

            SwiftEnum swiftEnum = new SwiftEnum(className, enumValues, this);
            StringBuilderUtil.appendln(classCode, swiftEnum.toSourceCode());
            EnumRule.enumToValues.remove(className);
        }

        return classCode.toString();
    }
}
