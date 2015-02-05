package org.jsonschema2pojo.formatters.swift.model;

import org.apache.commons.lang.StringUtils;
import org.jsonschema2pojo.exception.GenerationException;

/**
 * Created by Olivier Ziadé on 05/02/15.
 */
public abstract class SwiftDeclaration {

    protected int indentationLevel = 0;
    protected String name;
    protected SwiftDeclaration parent;

    protected abstract String declare() throws GenerationException;

    public String toSourceCode() throws GenerationException {
        return getIndentation() + declare();
    }

    public String getIndentation() {
        return indentationLevel > 0 ? StringUtils.repeat(SwiftConstants.INDENT_SYMBOL, indentationLevel) : "";
    }

    public int getIndentationLevel() {
        return indentationLevel;
    }

    public void setIndentationLevel(int indentationLevel) {
        this.indentationLevel = indentationLevel;
    }
}
