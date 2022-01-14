package com.ss.ita.rozetka.pageobject.utils;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.Configuration.*;
import static com.ss.ita.rozetka.pageobject.utils.PropertyHelper.*;

@Listeners(TestListener.class)
public class TestRunner {

    @BeforeClass
    protected void setBrowser() {
        browser = getBrowserProperty();
        browserSize = getBrowserSizeProperty();
        timeout = getTimeoutProperty();
        pageLoadTimeout = getPageLoadTimeout();
    }

    @AfterMethod
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }
}
