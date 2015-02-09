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
 * Swift field representation
 * Created by Olivier Ziadé on 04/02/15.
 */
public class SwiftField extends SwiftVar {
    
    private final String associatedName;
    
    /**
     * Constructor
     * @param name Name
     * @param type Type
     * @param parent Parent
     */
    public SwiftField(String name, SwiftType type, SwiftDeclaration parent) {
        super(name, type, parent);
        final SwiftStopWords stopWord = SwiftStopWords.fromString(this.name);
        this.associatedName = stopWord == null ? this.name : stopWord.getAssociatedName();
    }

    /**
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param type Type
     */
    public void setType(SwiftType type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String declare() throws GenerationException {
        if (this.name != null && this.type != null) {
            return DECLARATION_NAME + " " + getName()  + TYPE_SEPARATOR
                    + type.toSourceCode() + TYPE_OPTIONAL_SYMBOL;
        }
        
        throw new GenerationException("The field from " + parent.name + "does not have name and type.");
    }

    @Override
    public String getName() {
        return this.associatedName;
    }
    
    public String getRealName() {
        return this.name;
    }
}
