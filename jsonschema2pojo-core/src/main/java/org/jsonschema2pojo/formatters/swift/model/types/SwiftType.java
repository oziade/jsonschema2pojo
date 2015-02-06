package org.jsonschema2pojo.formatters.swift.model.types;

/**
 * Swift type wrapper
 * Created by Olivier Ziadé on 05/02/15.
 */
public abstract class SwiftType {
    protected final String name;

    /**
     * Constructor
     * @param name Name
     */
    public SwiftType(final String name) {
        this.name = name;
    }

    /**
     * @return Type source code
     */
    public abstract String toSourceCode();
}
