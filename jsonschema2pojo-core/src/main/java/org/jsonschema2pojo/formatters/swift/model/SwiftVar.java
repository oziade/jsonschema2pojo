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
import org.jsonschema2pojo.formatters.swift.model.types.SwiftType;

/**
 * Created by Olivier Ziadé on 06/02/15.
 */
public abstract class SwiftVar extends SwiftDeclaration {

    public static final String DECLARATION_NAME = "var";
    public static final String TYPE_SEPARATOR = ": ";
    public static final String TYPE_OPTIONAL_SYMBOL = "?";

    protected SwiftType type;

    /**
     * Constructor
     * @param parent Parent
     */
    public SwiftVar(final String name, final SwiftType type, final SwiftDeclaration parent) {
        Preconditions.checkNotNull(parent, "A variable must have a parent.");

        this.name = name;
        this.type = type;
        this.parent = parent;
        this.indentationLevel = parent.getIndentationLevel() + 1;
    }
}
