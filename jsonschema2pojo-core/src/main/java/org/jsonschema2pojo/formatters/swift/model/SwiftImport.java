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

/**
 * Swift import representation
 * Created by Olivier Ziadé on 05/02/15.
 */
public class SwiftImport extends SwiftDeclaration {
    public static final String DECLARATION_NAME = "import";

    /**
     * Constructor
     * @param name Name
     * @param parent Parent
     */
    public SwiftImport(final String name, final SwiftDeclaration parent) {
        Preconditions.checkNotNull(parent, "An import must have a parent.");

        this.name = name;
        this.parent = parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String declare() throws GenerationException {
        if (name != null) {
            return DECLARATION_NAME + " " + name;
        }

        throw new GenerationException("The import from " + parent.name + " does not have a name.");
    }
}
