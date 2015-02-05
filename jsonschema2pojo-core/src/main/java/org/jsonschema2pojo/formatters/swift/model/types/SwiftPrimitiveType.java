package org.jsonschema2pojo.formatters.swift.model.types;

/**
 * Created by Olivier Ziadé on 05/02/15.
 */
public class SwiftPrimitiveType extends SwiftType {

    public SwiftPrimitiveType(String name) {
        super(name);
    }

    @Override
    public String toSourceCode() {
        return name;
    }
}
