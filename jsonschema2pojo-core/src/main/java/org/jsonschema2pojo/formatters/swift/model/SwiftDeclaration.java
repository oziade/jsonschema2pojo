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

import org.apache.commons.lang.StringUtils;
import org.jsonschema2pojo.exception.GenerationException;

/**
 * Swift declaration wraps all other swift objects
 * Created by Olivier Ziadé on 05/02/15.
 */
public abstract class SwiftDeclaration {

    protected int indentationLevel = 0;
    protected String name;
    protected SwiftDeclaration parent;

    /**
     * @return Source code for the declaration
     * @throws GenerationException Generation exception
     */
    protected abstract String declare() throws GenerationException;

    /**
     * @return Indented source code
     * @throws GenerationException Generation exception
     */
    public String toSourceCode() throws GenerationException {
        return getIndentation() + declare();
    }

    /**
     * @return The declaration indentation
     */
    public String getIndentation() {
        return getIndentation(indentationLevel);
    }

    /**
     * @param level The level
     * @return  The indentation from level
     */
    public static String getIndentation(final int level) {
        return level > 0 ? StringUtils.repeat(SwiftConstants.INDENT_SYMBOL, level * 4) : "";
    }

    /**
     * @return The declaration indentation level
     */
    public int getIndentationLevel() {
        return indentationLevel;
    }

    public String getName() {
        return name;
    }
}
