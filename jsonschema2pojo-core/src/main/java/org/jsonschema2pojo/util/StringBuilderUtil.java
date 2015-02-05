package org.jsonschema2pojo.util;

import com.google.common.base.Preconditions;

/**
 * Created by Olivier Ziadé on 05/02/15.
 */
public final class StringBuilderUtil {

    private StringBuilderUtil() {
    }

    public static StringBuilder appendln(final StringBuilder builder, final String toAppend) {
        Preconditions.checkArgument(builder != null, "The builder must be set");
        Preconditions.checkArgument(toAppend != null, "The tring to append must be set");

        return builder.append(toAppend).append(String.format("%n"));
    }
}
