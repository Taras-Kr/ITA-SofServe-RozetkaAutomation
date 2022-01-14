package com.ss.ita.rozetka.pageobject.utils;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Base64;

import static java.lang.String.format;
import static org.apache.commons.lang3.time.DurationFormatUtils.formatDuration;

public class TestListener implements ITestListener {
    private static final Logger logger = Logger.getLogger("TEST");

    public TestListener() {
        DOMConfigurator.configure("src/test/resources/log4j.xml");
    }

    @Attachment(value = "pageScreenshot", type = "image/png")
    public static byte[] doScreenshot() {
        String screenshot = Selenide.screenshot(OutputType.BASE64);
        return Base64.getDecoder().decode(screenshot);
    }

    private static String getFormattedDurationOfTestExecution(ITestResult result, String formatTemplate) {
        return formatDuration(result.getEndMillis() - result.getStartMillis(), formatTemplate);
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Running test - " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test successfully completed");
        logger.info("Execution time: " + getFormattedDurationOfTestExecution(result, "mm:ss"));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test was skipped");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        logger.error("Test failed with TIMEOUT");
        logger.error("Execution time: " + getFormattedDurationOfTestExecution(result, "mm:ss"));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test FAILED");
        logger.error("Execution time: " + getFormattedDurationOfTestExecution(result, "mm:ss"));
        logger.error("Cause: " + result.getThrowable().getMessage());
        logger.debug(result.getThrowable());
        doScreenshot();
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("TESTING STARTS");
        logger.debug("Execution started in " + context.getStartDate());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("TESTING ENDS");
        var testResult = format("Test results: passed - %d, failed - %d, skipped - %d",
                context.getPassedTests().size(),
                context.getFailedTests().size(),
                context.getSkippedTests().size());
        logger.info(testResult);
        logger.debug("Execution ended in " + context.getEndDate());

        var generalTimeInMillis = context.getEndDate().getTime() - context.getStartDate().getTime();
        var formattedGeneralTime = formatDuration(generalTimeInMillis, "H:mm:ss");
        logger.info("General execution time: " + formattedGeneralTime);
    }
}
