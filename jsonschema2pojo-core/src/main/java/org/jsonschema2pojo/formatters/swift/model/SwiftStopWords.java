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

/**
 * Created by Olivier Ziadé on 09/02/15.
 */
public enum SwiftStopWords {
    WHERE("where", "where_");

    private String realName;
    private String associatedName;

    private SwiftStopWords(final String realName, final String associatedName) {
        this.realName = realName;
        this.associatedName = associatedName;
    }

    public String getAssociatedName() {
        return associatedName;
    }

    public String getRealName() {
        return realName;
    }

    /**
     * @param realName String real name
     * @return The corresponding enum
     */
    public static SwiftStopWords fromString(String realName) {
        for (SwiftStopWords word : SwiftStopWords.values()) {
            if (word.realName.equalsIgnoreCase(realName)) {
                return word;
            }
        }

        return null;
    }
}
