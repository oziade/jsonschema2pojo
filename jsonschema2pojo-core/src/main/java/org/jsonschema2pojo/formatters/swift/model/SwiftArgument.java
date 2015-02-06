package org.jsonschema2pojo.formatters.swift.model;

import org.jsonschema2pojo.exception.GenerationException;
import org.jsonschema2pojo.formatters.swift.model.types.SwiftType;

/**
 * Swift argument representation
 * Created by Olivier Ziadé on 06/02/15.
 */
public class SwiftArgument extends SwiftVar {
    
    /**
     * Constructor
     * @param name Name
     * @param type Type
     * @param parent Parent
     */
    public SwiftArgument(final String name, final SwiftType type, final SwiftDeclaration parent) {
        super(name, type, parent);
        this.indentationLevel = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String declare() throws GenerationException {
        if (this.name != null && this.type != null) {
            return name + TYPE_SEPARATOR + type.toSourceCode();
        }

        throw new GenerationException("The field from " + parent.name + "does not have name and type.");
    }
}
