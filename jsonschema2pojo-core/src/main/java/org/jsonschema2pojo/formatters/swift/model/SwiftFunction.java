package org.jsonschema2pojo.formatters.swift.model;

import com.google.common.base.Preconditions;
import org.jsonschema2pojo.exception.GenerationException;
import org.jsonschema2pojo.util.StringBuilderUtil;

import java.util.List;

/**
 * Swift function representation
 * Created by Olivier Ziadé on 05/02/15.
 */
public class SwiftFunction extends SwiftDeclaration {

    public static final String OVERRIDE_SYMBOL = "override";
    protected String declarationName = "func";
    protected String body;
    protected List<SwiftArgument> arguments;
    protected final boolean override;

    /**
     * Constructor
     * @param name   Name
     * @param body   Body
     * @param override Does this method override a superclass method ?
     * @param parent Parent
     */
    public SwiftFunction(final String name, final List<SwiftArgument> arguments, final String body, boolean override, final SwiftDeclaration parent) {
        Preconditions.checkNotNull(parent, "A function must have a parent.");

        this.name = name;
        this.arguments = arguments;
        this.body = body;
        this.parent = parent;
        this.indentationLevel = parent.getIndentationLevel() + 1;
        this.override = override;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String declare() throws GenerationException {
        if (name != null && body != null) {
            StringBuilder sourceCode = new StringBuilder();
            if (override) {
                sourceCode.append(OVERRIDE_SYMBOL + " ");
            } 
            
            StringBuilderUtil.appendln(sourceCode, declarationName + " " + name + "(" + formatArguments() + ") {");
            if (!body.isEmpty()) {
                StringBuilderUtil.appendln(sourceCode, body);
            }
            StringBuilderUtil.appendln(sourceCode, getIndentation() + "}");

            return sourceCode.toString();
        }

        throw new GenerationException("The function from " + parent.name + "does not have name and type.");
    }

    /**
     * Format arguments for code generation
     * @return Formatted arguments
     */
    protected String formatArguments() {
        if (!arguments.isEmpty()) {

            StringBuilder parseBuilder = new StringBuilder();
            for (SwiftArgument argument : arguments) {
                parseBuilder.append(argument.toSourceCode() + ", ");
            }

            final String parsedArguments = parseBuilder.toString();
            return parsedArguments.substring(0, parsedArguments.length() - 2);
        }

        return "";
    }
}
