package org.jsonschema2pojo.formatters.swift.model.types;

/**
 * Created by Olivier Ziadé on 05/02/15.
 */
public class SwiftArrayType extends SwiftType {

    public final String typeName;

    public SwiftArrayType(String name, String typeName) {
        super(name);
        this.typeName = typeName;
    }

    @Override
    public String toSourceCode() {
        return "[" + typeName + "]";
    }
}
