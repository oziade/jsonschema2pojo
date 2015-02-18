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

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import org.jsonschema2pojo.exception.GenerationException;
import org.jsonschema2pojo.formatters.swift.SwiftCodeModel;
import org.jsonschema2pojo.formatters.swift.model.types.SwiftDate;
import org.jsonschema2pojo.formatters.swift.model.types.SwiftPrimitiveType;
import org.jsonschema2pojo.util.StringBuilderUtil;

import java.util.ArrayList;
import java.util.Map;

/**
 * Swift map function (for ObjectMapper)
 * Created by Olivier Ziadé on 06/02/15.
 */
public class SwiftMapFunction extends SwiftFunction {

    private JDefinedClass superClass;

    /**
     * Constructor
     * @param name     Name
     * @param override Does this method override a superclass method ?
     * @param parent   Parent
     */
    public SwiftMapFunction(final String name, final boolean override, final SwiftClass parent) {
        super(name, new ArrayList<SwiftArgument>(), "", override, parent);
        this.arguments.add(new SwiftArgument("map", new SwiftPrimitiveType("Map"), this));
        this.superClass = null;

        final JDefinedClass definedClass = parent.getDefinedClass();
        if (override && definedClass != null) {
            // Hack for giving access to super class fields to generate map function
            // TODO Find another way to do this, because it's evil
            this.superClass = (JDefinedClass) definedClass._extends();
        }
    }

    /**
     * Add a statement to body
     * @param field Swift field
     */
    public void addStatement(SwiftField field) {
        String statement;
        if(SwiftDate.DATE_TYPE_NAME.equals(field.getType().toSourceCode())) {
            statement = getIndentation(indentationLevel + 2) + field.getName() + " <= (" + name + "[\"" + field.getRealName() + "\"], ISO8601UTCDateTransform<"+ field.getType().toSourceCode() + ", String>())" + String.format("%n");
        } else {
            statement = getIndentation(indentationLevel + 2) + field.getName() + " <= " + name + "[\"" + field.getRealName() + "\"]" + String.format("%n");
        }
         
        this.body += statement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String declare() throws GenerationException {
        if (name != null && body != null) {
            StringBuilder sourceCode = new StringBuilder();
            if (override) {
                sourceCode.append("override ");
            }

            StringBuilderUtil.appendln(sourceCode, declarationName + " " + name + "(" + formatArguments() + ") {");

            if (override) {
                for (Map.Entry<String, JFieldVar> entry : superClass.fields().entrySet()) {
                    final String key = entry.getKey();
                    if (!"additionalProperties".equals(key)) {
                        SwiftField field = new SwiftField(key, SwiftCodeModel.parseType(entry.getValue().type()), this);
                        addStatement(field);
                    }
                }
            }

            if (!body.isEmpty()) {
                StringBuilderUtil.appendln(sourceCode, body);
            }
            StringBuilderUtil.appendln(sourceCode, getIndentation() + "}");

            return sourceCode.toString();
        }

        throw new GenerationException("The function from " + parent.name + "does not have name and type.");
    }
}
