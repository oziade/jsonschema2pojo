package org.jsonschema2pojo.formatters.swift.model.types;

/**
 * Swift array
 * Created by Olivier Ziadé on 05/02/15.
 */
public class SwiftArrayType extends SwiftType {

    public final String itemType;

    /**
     * Constructor
     * @param name Array name
     * @param itemType Array type for items
     */
    public SwiftArrayType(final String name, final String itemType) {
        super(name);
        this.itemType = itemType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSourceCode() {
        return "[" + itemType + "]";
    }
}
