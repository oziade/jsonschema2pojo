package org.jsonschema2pojo.formatters.swift.model;

/**
 * Created by Olivier Ziadé on 05/02/15.
 */
public class SwiftConstructor extends SwiftFunction {

    public static final String CONSTRUCTOR_NAME = "init";

    public SwiftConstructor(SwiftDeclaration parent) {
        super(CONSTRUCTOR_NAME, "", parent);
        this.DECLARATION_NAME = SwiftConstants.REQUIRED;
    }
}
