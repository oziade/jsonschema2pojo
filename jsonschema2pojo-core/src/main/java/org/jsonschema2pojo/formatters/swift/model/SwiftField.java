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

import com.google.common.base.Preconditions;
import org.jsonschema2pojo.exception.GenerationException;
import org.jsonschema2pojo.formatters.swift.model.types.SwiftType;

/**
 * Created by Olivier Ziadé on 04/02/15.
 */
public class SwiftField extends SwiftDeclaration {
    public static final String DECLARATION_NAME = "var";
    public static final String TYPE_SEPARATOR = ": ";
    public static final String TYPE_OPTIONAL_SYMBOL = "?";

    private String name;
    private SwiftType type;
    private SwiftDeclaration parent;

    public SwiftField(final SwiftDeclaration parent) {
        Preconditions.checkNotNull(parent, "A field must have a parent.");

        this.name = null;
        this.type = null;
        this.parent = parent;
        this.indentationLevel = parent.getIndentationLevel() + 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SwiftType getType() {
        return type;
    }

    public void setType(SwiftType type) {
        this.type = type;
    }

    @Override
    protected String declare() throws GenerationException {
        if (this.name != null && this.type != null) {
            return DECLARATION_NAME + " " + name + TYPE_SEPARATOR
                    + type.toSourceCode() + TYPE_OPTIONAL_SYMBOL;
        }
        throw new GenerationException("The field from " + parent.name + "does not have name and type.");
    }
}
