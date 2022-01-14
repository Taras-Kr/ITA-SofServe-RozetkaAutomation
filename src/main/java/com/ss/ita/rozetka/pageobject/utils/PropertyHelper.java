package com.ss.ita.rozetka.pageobject.utils;

import lombok.experimental.UtilityClass;

import java.time.Duration;

@UtilityClass
public class PropertyHelper {
    public static String getBrowserProperty() {
        return System.getProperty("browser", "chrome");
    }

    public static String getBrowserSizeProperty() {
        return System.getProperty("size", "1366x768");
    }

    public static long getTimeoutProperty() {
        String defaultTimeout = String.valueOf(Duration.ofMinutes(2).toMillis());
        String systemTimeout = System.getProperty("timeout", defaultTimeout);
        return Long.parseLong(systemTimeout);
    }

    public static long getPageLoadTimeout() {
        var systemPageLoadTimeout = System.getProperty("pageLoadTimeout", String.valueOf(3*60*1000));
        return Long.parseLong(systemPageLoadTimeout);
    }
}
