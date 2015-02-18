package org.jsonschema2pojo.formatters.swift.model.types;

/**
 * Created by Olivier Ziadé on 18/02/15.
 */
public class SwiftDate  extends SwiftType {

    public static final String DATE_TYPE_NAME = "NSDate";

    /**
     * Constructor
     * @param name Name
     */
    public SwiftDate(String name) {
        super(name);
    }

    @Override
    public String toSourceCode() {
        return DATE_TYPE_NAME;
    }
}
