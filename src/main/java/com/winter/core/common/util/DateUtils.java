/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.common.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * @author likailee.llk
 * @version DateUtils.java 2020/11/27 Fri 9:24 PM likai
 */
public class DateUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofLocalizedDateTime(FormatStyle.MEDIUM)
            .withLocale(Locale.CHINA)
            .withZone(ZoneId.systemDefault());

    public static String now() {
        return FORMATTER.format(Instant.now());
    }
}
