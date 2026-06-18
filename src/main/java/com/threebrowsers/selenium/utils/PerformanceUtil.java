package com.threebrowsers.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PerformanceUtil {

    private static final String DEFAULT_TIMEOUT_SECONDS = "20";

    public static long measurePageLoad(WebDriver driver, Runnable navigationAction, By pageLoadedLocator) {
        String timeoutProp = System.getProperty("webdriver.wait.timeout", DEFAULT_TIMEOUT_SECONDS);
        long timeoutInSeconds;
        try {
            timeoutInSeconds = Long.parseLong(timeoutProp);
        } catch (NumberFormatException e) {
            timeoutInSeconds = Long.parseLong(DEFAULT_TIMEOUT_SECONDS);
        }

        long startTime = System.currentTimeMillis();

        navigationAction.run();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageLoadedLocator));

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}