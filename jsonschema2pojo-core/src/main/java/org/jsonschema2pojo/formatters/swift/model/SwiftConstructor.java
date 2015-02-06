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
