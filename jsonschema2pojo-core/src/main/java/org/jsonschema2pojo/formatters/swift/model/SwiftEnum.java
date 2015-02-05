package org.jsonschema2pojo.formatters.swift.model;

import com.google.common.base.Preconditions;
import org.jsonschema2pojo.exception.GenerationException;
import org.jsonschema2pojo.util.StringBuilderUtil;

import java.util.List;

/**
 * Created by Olivier Ziadé on 05/02/15.
 */
public class SwiftEnum extends SwiftDeclaration {

    public static final String DECLARATION_NAME = "enum";
    public static final String DECLARATION_ITEM_NAME = "case";
    private final List<String> enumConstants;
    private final SwiftDeclaration parent;

    public SwiftEnum(final String name, final List<String> enumConstants, SwiftDeclaration parent) {
        Preconditions.checkNotNull(parent, "An enum must have a parent.");

        this.name = name;
        this.enumConstants = enumConstants;
        this.parent = parent;
        this.indentationLevel = parent.getIndentationLevel() + 1;
    }

    @Override
    protected String declare() throws GenerationException {
        if (name != null && enumConstants != null) {
            StringBuilder sourceCode = new StringBuilder();
            StringBuilderUtil.appendln(sourceCode, DECLARATION_NAME + " " + name + " {");
            StringBuilderUtil.appendln(sourceCode, parseConstants());
            StringBuilderUtil.appendln(sourceCode, getIndentation() + "}");

            return sourceCode.toString();
        }

        throw new GenerationException("The function from " + parent.name + "does not have name and enum constants.");
    }

    private String parseConstants() {
        Preconditions.checkNotNull(enumConstants, "No enum value found");

        StringBuilder sourceCode = new StringBuilder();

        for (String constant : enumConstants) {
            StringBuilderUtil.appendln(sourceCode, SwiftConstants.INDENT_SYMBOL + DECLARATION_ITEM_NAME + " " + constant);
        }

        return sourceCode.toString();
    }
}
