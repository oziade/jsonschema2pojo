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
