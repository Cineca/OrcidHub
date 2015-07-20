/**
 * This file is part of huborcid.
 *
 * huborcid is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * huborcid is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with huborcid.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.cineca.pst.huborcid.web.propertyeditors;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * Custom PropertyEditorSupport to convert from String to
 * Date using JodaTime (http://www.joda.org/joda-time/).
 */
public class LocaleDateTimeEditor extends PropertyEditorSupport {

    private final DateTimeFormatter formatter;

    private final boolean allowEmpty;

    /**
     * Create a new LocaleDateTimeEditor instance, using the given format for
     * parsing and rendering.
     * <p/>
     * The "allowEmpty" parameter states if an empty String should be allowed
     * for parsing, i.e. get interpreted as null value. Otherwise, an
     * IllegalArgumentException gets thrown.
     *
     * @param dateFormat DateFormat to use for parsing and rendering
     * @param allowEmpty if empty strings should be allowed
     */
    public LocaleDateTimeEditor(String dateFormat, boolean allowEmpty) {
        this.formatter = DateTimeFormat.forPattern(dateFormat);
        this.allowEmpty = allowEmpty;
    }

    /**
     * Format the YearMonthDay as String, using the specified format.
     *
     * @return DateTime formatted string
     */
    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        return value != null ? new LocalDateTime(value).toString(formatter) : "";
    }

    /**
     * Parse the value from the given text, using the specified format.
     *
     * @param text the text to format
     * @throws IllegalArgumentException
     */
    @Override
    public void setAsText( String text ) throws IllegalArgumentException {
        if ( allowEmpty && !StringUtils.hasText(text) ) {
            // Treat empty String as null value.
            setValue(null);
        } else {
            setValue(new LocalDateTime(formatter.parseDateTime(text)));
        }
    }
}
