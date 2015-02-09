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

package org.jsonschema2pojo.util;

import com.google.common.base.Preconditions;

/**
 * Created by Olivier Ziadé on 05/02/15.
 */
public final class StringBuilderUtil {

    private StringBuilderUtil() {
    }

    public static StringBuilder appendln(final StringBuilder builder, final String toAppend) {
        Preconditions.checkArgument(builder != null, "The builder must be set");
        Preconditions.checkArgument(toAppend != null, "The tring to append must be set");

        return builder.append(toAppend).append(String.format("%n"));
    }
}
