package org.jsonschema2pojo.formatters.swift.model.types;

/**
 * Created by Olivier Ziadé on 05/02/15.
 */
public abstract class SwiftType {
    protected final String name;

    public SwiftType(String name) {
        this.name = name;
    }

    public abstract String toSourceCode();
}
