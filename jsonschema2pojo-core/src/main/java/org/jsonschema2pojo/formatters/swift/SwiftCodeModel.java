package org.jsonschema2pojo.formatters.swift;

import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JType;
import org.jsonschema2pojo.formatters.CodeModel;
import org.jsonschema2pojo.formatters.SupportedLanguage;
import org.jsonschema2pojo.formatters.swift.model.SwiftClass;
import org.jsonschema2pojo.formatters.swift.model.types.SwiftArrayType;
import org.jsonschema2pojo.formatters.swift.model.types.SwiftPrimitiveType;
import org.jsonschema2pojo.formatters.swift.model.types.SwiftType;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Iterator;

/**
 * Swift code model
 * Created by Olivier Ziadé on 04/02/15.
 */
public class SwiftCodeModel extends CodeModel {
    public static final SwiftType BOOLEAN = new SwiftPrimitiveType("Bool");
    public static final SwiftType INTEGER = new SwiftPrimitiveType("Int");

    /**
     * Constructor
     * @param codeModel Code model
     */
    public SwiftCodeModel(final JCodeModel codeModel) {
        super(codeModel);
        this.language = SupportedLanguage.SWIFT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void build(final CodeWriter sourceWriter, final CodeWriter resourcesWriter) throws IOException {
        for (Iterator<JPackage> pkgIt = codeModel.packages(); pkgIt.hasNext(); ) {
            JPackage pkg = pkgIt.next();

            // Write classes
            for (Iterator<JDefinedClass> classIt = pkg.classes(); classIt.hasNext(); ) {
                JDefinedClass c = classIt.next();
                if (c.isHidden())
                    continue;   // don't generate this file

                PrintWriter pw = createSwiftSourceFileWriter(pkg, sourceWriter, c.name());
                SwiftClass swiftClass = new SwiftClass(c);
                pw.println(swiftClass.toSourceCode());
                pw.close();
            }
        }

        sourceWriter.close();
        resourcesWriter.close();
    }

    /**
     * Creates the swift source file on disk
     * @param pkg Package
     * @param src Source writer
     * @param className Class name
     * @return Source writer
     * @throws IOException IOException
     */
    private PrintWriter createSwiftSourceFileWriter(final JPackage pkg, final CodeWriter src, final String className) throws IOException {
        Writer bw = new BufferedWriter(src.openSource(pkg, className + ".swift"));
        return new PrintWriter(bw);
    }

    /**
     * Extract swift type from java type
     * @param javaType Java type
     * @return Swift type
     */
    public static SwiftType parseType(final JType javaType) {
        SwiftType type;
        final String javaTypeName = javaType.name();
        if ("Integer".equals(javaTypeName)) {
            type = SwiftCodeModel.INTEGER;
        } else if ("Boolean".equals(javaTypeName)) {
            type = SwiftCodeModel.BOOLEAN;
        } else if ("Double".equals(javaTypeName)) {
            type = new SwiftPrimitiveType(javaTypeName);
        } else if (javaTypeName.startsWith("List")) {
            type = new SwiftArrayType(javaTypeName,
                    javaTypeName.substring(javaTypeName.indexOf("<") + 1, javaTypeName.lastIndexOf(">")));
        } else {
            type = new SwiftPrimitiveType(javaTypeName);
        }

        return type;
    }
}
