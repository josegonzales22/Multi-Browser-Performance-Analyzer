package com.threebrowsers.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PerformanceUtil {
    public static long measurePageLoad(WebDriver driver, Runnable navigationAction, By pageLoadedLocator) {
        long startTime = System.currentTimeMillis();

        navigationAction.run();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageLoadedLocator));

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}