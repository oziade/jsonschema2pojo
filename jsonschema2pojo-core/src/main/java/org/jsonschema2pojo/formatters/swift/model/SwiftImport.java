package org.jsonschema2pojo.formatters.swift.model;

import com.google.common.base.Preconditions;
import org.jsonschema2pojo.exception.GenerationException;

/**
 * Swift import representation
 * Created by Olivier Ziadé on 05/02/15.
 */
public class SwiftImport extends SwiftDeclaration {
    public static final String DECLARATION_NAME = "import";

    /**
     * Constructor
     * @param name Name
     * @param parent Parent
     */
    public SwiftImport(final String name, final SwiftDeclaration parent) {
        Preconditions.checkNotNull(parent, "An import must have a parent.");

        this.name = name;
        this.parent = parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String declare() throws GenerationException {
        if (name != null) {
            return DECLARATION_NAME + " " + name;
        }

        throw new GenerationException("The import from " + parent.name + " does not have a name.");
    }
}
