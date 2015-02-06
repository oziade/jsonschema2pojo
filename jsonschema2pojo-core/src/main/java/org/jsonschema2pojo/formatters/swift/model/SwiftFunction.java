package org.jsonschema2pojo.formatters.swift.model;

import com.google.common.base.Preconditions;
import org.jsonschema2pojo.exception.GenerationException;
import org.jsonschema2pojo.util.StringBuilderUtil;

/**
 * Swift function representation
 * Created by Olivier Ziadé on 05/02/15.
 */
public class SwiftFunction extends SwiftDeclaration {

    protected String declarationName = "func";
    protected final String body;

    /**
     * Constructor
     * @param name   Name
     * @param body   Body
     * @param parent Parent
     */
    public SwiftFunction(final String name, final String body, final SwiftDeclaration parent) {
        Preconditions.checkNotNull(parent, "A function must have a parent.");

        this.name = name;
        this.body = body;
        this.parent = parent;
        this.indentationLevel = parent.getIndentationLevel() + 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String declare() throws GenerationException {
        if (name != null && body != null) {
            StringBuilder sourceCode = new StringBuilder();
            StringBuilderUtil.appendln(sourceCode, declarationName + " " + name + "() {");
            StringBuilderUtil.appendln(sourceCode, SwiftConstants.INDENT_SYMBOL + body);
            StringBuilderUtil.appendln(sourceCode, getIndentation() + "}");

            return sourceCode.toString();
        }

        throw new GenerationException("The function from " + parent.name + "does not have name and type.");
    }
}
