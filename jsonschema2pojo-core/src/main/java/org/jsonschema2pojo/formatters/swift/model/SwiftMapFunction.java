package org.jsonschema2pojo.formatters.swift.model;

import org.jsonschema2pojo.formatters.swift.model.types.SwiftPrimitiveType;

import java.util.ArrayList;

/**
 * Swift map function (for ObjectMapper)
 * Created by Olivier Ziadé on 06/02/15.
 */
public class SwiftMapFunction extends SwiftFunction {
    

    /**
     * Constructor
     * @param name   Name
     * @param parent Parent
     */
    public SwiftMapFunction(final String name, final SwiftDeclaration parent) {
        super(name, new ArrayList<SwiftArgument>(), "",  parent);
        this.arguments.add(new SwiftArgument("map", new SwiftPrimitiveType("Map"), this));
    }

    /**
     * Add a statement to body
     * @param field Swift field
     */
    public void addStatement(SwiftField field) {
        this.body +=  getIndentation(indentationLevel + 2) + field.getName() + " <= " + name + "[\"" + field.getName() + "\"]" + String.format("%n");
    }
}
