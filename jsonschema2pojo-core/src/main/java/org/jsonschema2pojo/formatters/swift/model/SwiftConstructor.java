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

import java.util.ArrayList;

/**
 * Swift constructor representation
 * Created by Olivier Ziadé on 05/02/15.
 */
public class SwiftConstructor extends SwiftFunction {

    public static final String CONSTRUCTOR_NAME = "init";

    /**
     * Constructor
     * @param parent Parent
     */
    public SwiftConstructor(final SwiftDeclaration parent) {
        super(CONSTRUCTOR_NAME, new ArrayList<SwiftArgument>(), "", parent);

        Preconditions.checkNotNull(parent, "An constructor must have a parent.");
        this.declarationName = SwiftConstants.REQUIRED;
    }
}
