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

import org.jsonschema2pojo.formatters.swift.model.types.SwiftPrimitiveType;

import java.util.ArrayList;

/**
 * Swift map function (for ObjectMapper)
 * Created by Olivier Ziadé on 06/02/15.
 */
public class SwiftMapFunction extends SwiftFunction {
    

    /**
     * Constructor
     * @param name   Name
     * @param parent Parent
     */
    public SwiftMapFunction(final String name, final SwiftDeclaration parent) {
        super(name, new ArrayList<SwiftArgument>(), "",  parent);
        this.arguments.add(new SwiftArgument("map", new SwiftPrimitiveType("Map"), this));
    }

    /**
     * Add a statement to body
     * @param field Swift field
     */
    public void addStatement(SwiftField field) {
        this.body +=  getIndentation(indentationLevel + 2) + field.getName() + " <= " + name + "[\"" + field.getRealName() + "\"]" + String.format("%n");
    }
}
