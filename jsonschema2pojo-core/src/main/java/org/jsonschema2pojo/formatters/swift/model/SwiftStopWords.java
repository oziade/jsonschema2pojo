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
