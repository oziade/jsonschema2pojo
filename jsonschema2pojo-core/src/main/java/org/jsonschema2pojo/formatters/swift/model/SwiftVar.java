package org.jsonschema2pojo.formatters.swift.model;

import com.google.common.base.Preconditions;
import org.jsonschema2pojo.formatters.swift.model.types.SwiftType;

/**
 * Created by Olivier Ziadé on 06/02/15.
 */
public abstract class SwiftVar extends SwiftDeclaration {

    public static final String DECLARATION_NAME = "var";
    public static final String TYPE_SEPARATOR = ": ";
    public static final String TYPE_OPTIONAL_SYMBOL = "?";

    protected SwiftType type;

    /**
     * Constructor
     * @param parent Parent
     */
    public SwiftVar(final String name, final SwiftType type, final SwiftDeclaration parent) {
        Preconditions.checkNotNull(parent, "A variable must have a parent.");

        this.name = name;
        this.type = type;
        this.parent = parent;
        this.indentationLevel = parent.getIndentationLevel() + 1;
    }
}
