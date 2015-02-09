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
import org.jsonschema2pojo.util.StringBuilderUtil;

import java.util.List;

/**
 * Swift enum representation
 * Created by Olivier Ziadé on 05/02/15.
 */
public class SwiftEnum extends SwiftDeclaration {

    public static final String DECLARATION_NAME = "enum ";
    public static final String DECLARATION_ITEM_NAME = "case ";
    private final List<String> enumValues;

    /**
     * Constructor
     * @param name Enum name
     * @param enumValues Enum values
     * @param parent Parent
     */
    public SwiftEnum(final String name, final List<String> enumValues, final SwiftDeclaration parent) {
        Preconditions.checkNotNull(parent, "An enum must have a parent.");

        this.name = name;
        this.enumValues = enumValues;
        this.parent = parent;
        this.indentationLevel = parent.getIndentationLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String declare() throws GenerationException {
        if (name != null && enumValues != null) {
            StringBuilder sourceCode = new StringBuilder();
            StringBuilderUtil.appendln(sourceCode, DECLARATION_NAME + name + " {");
            StringBuilderUtil.appendln(sourceCode, parseValues());
            StringBuilderUtil.appendln(sourceCode, getIndentation() + "}");

            return sourceCode.toString();
        }

        throw new GenerationException("The enum from " + name + "does not have name and enum values.");
    }

    /**
     * @return The enum values
     */
    private String parseValues() {
        Preconditions.checkNotNull(enumValues, "No enum value found");

        StringBuilder sourceCode = new StringBuilder(getIndentation(indentationLevel + 1) + DECLARATION_ITEM_NAME);
        for (String constant : enumValues) {
            sourceCode.append(constant + ", ");
        }

        return sourceCode.toString().substring(0, sourceCode.length() - 2);
    }
}
