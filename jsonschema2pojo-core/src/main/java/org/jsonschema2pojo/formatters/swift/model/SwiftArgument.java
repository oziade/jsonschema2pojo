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

import org.jsonschema2pojo.exception.GenerationException;
import org.jsonschema2pojo.formatters.swift.model.types.SwiftType;

/**
 * Swift argument representation
 * Created by Olivier Ziadé on 06/02/15.
 */
public class SwiftArgument extends SwiftVar {
    
    /**
     * Constructor
     * @param name Name
     * @param type Type
     * @param parent Parent
     */
    public SwiftArgument(final String name, final SwiftType type, final SwiftDeclaration parent) {
        super(name, type, parent);
        this.indentationLevel = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String declare() throws GenerationException {
        if (this.name != null && this.type != null) {
            return name + TYPE_SEPARATOR + type.toSourceCode();
        }

        throw new GenerationException("The field from " + parent.name + "does not have name and type.");
    }
}
