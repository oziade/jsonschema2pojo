package org.jsonschema2pojo.formatters.swift.model;

import org.apache.commons.lang.StringUtils;
import org.jsonschema2pojo.exception.GenerationException;

/**
 * Swift declaration wraps all other swift objects
 * Created by Olivier Ziadé on 05/02/15.
 */
public abstract class SwiftDeclaration {

    protected int indentationLevel = 0;
    protected String name;
    protected SwiftDeclaration parent;

    /**
     * @return Source code for the declaration
     * @throws GenerationException Generation exception
     */
    protected abstract String declare() throws GenerationException;

    /**
     * @return Indented source code
     * @throws GenerationException Generation exception
     */
    public String toSourceCode() throws GenerationException {
        return getIndentation() + declare();
    }

    /**
     * @return The declaration indentation
     */
    public String getIndentation() {
        return getIndentation(indentationLevel);
    }

    /**
     * @param level The level
     * @return  The indentation from level
     */
    public static String getIndentation(final int level) {
        return level > 0 ? StringUtils.repeat(SwiftConstants.INDENT_SYMBOL, level) : "";
    }

    /**
     * @return The declaration indentation level
     */
    public int getIndentationLevel() {
        return indentationLevel;
    }
}
