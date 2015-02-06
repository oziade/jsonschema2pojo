package org.jsonschema2pojo.cli;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import org.jsonschema2pojo.formatters.SupportedLanguage;

/**
 * Converter for {@link org.jsonschema2pojo.formatters.SupportedLanguage} argument
 * Created by Olivier Ziadé on 06/02/15.
 */
public class SupportedLanguageConverter implements IStringConverter<SupportedLanguage> {

    /**
     * {@inheritDoc}
     */
    @Override
    public SupportedLanguage convert(String value) {
        SupportedLanguage language = SupportedLanguage.fromString(value);

        if(language == null) {
            throw new ParameterException("Value " + value + " can not be converted. Supported language are: JAVA, SWIFT.");
        }

        return language;
    }
}
