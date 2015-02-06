/**
 * Copyright © 2010-2014 Nokia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jsonschema2pojo;

import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JCodeModel;
import org.jsonschema2pojo.exception.GenerationException;
import org.jsonschema2pojo.formatters.CodeModel;
import org.jsonschema2pojo.formatters.java.JavaCodeModel;
import org.jsonschema2pojo.formatters.swift.SwiftCodeModel;
import org.jsonschema2pojo.rules.RuleFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.defaultString;
import static org.apache.commons.lang3.StringUtils.substringBeforeLast;

public class Jsonschema2Pojo {
    /**
     * Reads the contents of the given source and initiates schema generation.
     * @param config the configuration options (including source and target paths,
     *               and other behavioural options) that will control code
     *               generation
     * @throws FileNotFoundException if the source path is not found
     * @throws IOException           if the application is unable to read data from the source
     */
    public static void generate(GenerationConfig config) throws FileNotFoundException, IOException {
        Annotator annotator = getAnnotator(config);
        RuleFactory ruleFactory = createRuleFactory(config);

        ruleFactory.setAnnotator(annotator);
        ruleFactory.setGenerationConfig(config);

        SchemaMapper mapper = new SchemaMapper(ruleFactory, new SchemaGenerator());

        JCodeModel codeModel = new JCodeModel();

        if (config.isRemoveOldOutput()) {
            removeOldOutput(config.getTargetDirectory());
        }

        for (Iterator<File> sources = config.getSource(); sources.hasNext(); ) {
            File source = sources.next();

            if (source.isDirectory()) {
                generateRecursive(config, mapper, codeModel, defaultString(config.getTargetPackage()), Arrays.asList(source.listFiles(config.getFileFilter())));
            } else {
                mapper.generate(codeModel, getNodeName(source), defaultString(config.getTargetPackage()), source.toURI().toURL());
            }
        }

        if (config.getTargetDirectory().exists() || config.getTargetDirectory().mkdirs()) {
            CodeWriter sourcesWriter = new FileCodeWriterWithEncoding(config.getTargetDirectory(), config.getOutputEncoding());
            CodeWriter resourcesWriter = new FileCodeWriterWithEncoding(config.getTargetDirectory(), config.getOutputEncoding());
            CodeModel model;

            switch (config.getLanguage()) {
                case SWIFT:
                    model = new SwiftCodeModel(codeModel);
                    break;
                default:
                    model = new JavaCodeModel(codeModel);
            }

            model.build(sourcesWriter, resourcesWriter);
        } else {
            throw new GenerationException("Could not create or access target directory " + config.getTargetDirectory().getAbsolutePath());
        }
    }

    private static RuleFactory createRuleFactory(GenerationConfig config) {
        Class<? extends RuleFactory> clazz = config.getCustomRuleFactory();

        if (!RuleFactory.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("The class name given as a rule factory  (" + clazz.getName() + ") does not refer to a class that implements " + RuleFactory.class.getName());
        }

        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalArgumentException("Failed to create a rule factory from the given class. An exception was thrown on trying to create a new instance.", e.getCause());
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Failed to create a rule factory from the given class. It appears that we do not have access to this class - is both the class and its no-arg constructor marked public?", e);
        }
    }

    private static void generateRecursive(GenerationConfig config, SchemaMapper mapper, JCodeModel codeModel, String packageName, List<File> schemaFiles) throws FileNotFoundException, IOException {
        Collections.sort(schemaFiles);

        for (File child : schemaFiles) {
            if (child.isFile()) {
                mapper.generate(codeModel, getNodeName(child), defaultString(packageName), child.toURI().toURL());
            } else {
                generateRecursive(config, mapper, codeModel, packageName + "." + child.getName(), Arrays.asList(child.listFiles(config.getFileFilter())));
            }
        }
    }

    private static void removeOldOutput(File targetDirectory) {
        if (targetDirectory.exists()) {
            for (File f : targetDirectory.listFiles()) {
                delete(f);
            }
        }
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings(value = "RV_RETURN_VALUE_IGNORED_BAD_PRACTICE")
    private static void delete(File f) {
        if (f.isDirectory()) {
            for (File child : f.listFiles()) {
                delete(child);
            }
        }
        f.delete();
    }

    private static Annotator getAnnotator(GenerationConfig config) {
        AnnotatorFactory factory = new AnnotatorFactory();
        return factory.getAnnotator(
                factory.getAnnotator(config.getAnnotationStyle()),
                factory.getAnnotator(config.getCustomAnnotator()));
    }

    private static String getNodeName(File file) {
        return substringBeforeLast(file.getName(), ".");
    }
}
