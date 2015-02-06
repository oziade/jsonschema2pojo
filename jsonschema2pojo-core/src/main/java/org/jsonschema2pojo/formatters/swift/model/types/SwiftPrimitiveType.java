package org.jsonschema2pojo.formatters.swift.model.types;

/**
 * Swift primitive type
 * Created by Olivier Ziadé on 05/02/15.
 */
public class SwiftPrimitiveType extends SwiftType {

    /**
     * Constructor
     * @param name Name
     */
    public SwiftPrimitiveType(final String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSourceCode() {
        return name;
    }
}
