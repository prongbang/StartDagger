package com.touchtechnologies.startdagger.utils;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Created by dev-touch on 4/10/2017.
 */

public class DateUtil {

    private static final DateTimeFormatter parser = ISODateTimeFormat.dateTime();

    public static long parseDate(String input) {
        return parser.parseDateTime(input).getMillis();
    }

}
