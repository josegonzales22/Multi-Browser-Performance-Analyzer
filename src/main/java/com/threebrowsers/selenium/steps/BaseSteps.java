package com.threebrowsers.selenium.steps;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.threebrowsers.selenium.images.ScreenshotUtil;
import com.threebrowsers.selenium.utils.MetricsUtil;
import com.threebrowsers.selenium.utils.PerformanceUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BaseSteps {
    protected final WebDriver driver;
    protected final String browserName;
    protected final ExtentTest test;

    public BaseSteps(WebDriver driver, String browserName, ExtentTest test) {
        this.driver = driver;
        this.browserName = browserName;
        this.test = test;
        ScreenshotUtil.resetCounter(browserName);
    }

    protected void measureAndLogPageLoad(String stepName, String description, Runnable action, By locator) {
        long loadTime = PerformanceUtil.measurePageLoad(driver, action, locator);
        MetricsUtil.addTime(loadTime);

        String logMessage = String.format("%s (⏱ %d ms)", description, loadTime);
        logStepWithScreenshot(stepName, logMessage);
    }

    protected void assertAndLogPerformanceMetrics() {
        double p95 = MetricsUtil.getP95();
        long min = MetricsUtil.getMin();
        long max = MetricsUtil.getMax();
        double median = MetricsUtil.getMedian();

        String metricsSummary = String.format(
                "<b>📊 Performance Summary (%s):</b><br/>" +
                        "• Min Time: %d ms<br/>" +
                        "• Max Time: %d ms<br/>" +
                        "• Median: %.2f ms<br/>" +
                        "• <b>Percentile 95 (P95): %.2f ms</b>",
                browserName.toUpperCase(), min, max, median, p95
        );

        test.info(metricsSummary);

        if (p95 > 3000) {
            test.fail("❌ Performance Failure: The 95th Percentile (" + p95 + " ms) exceeds the critical limit of 3000 ms");
            throw new AssertionError("The 95th Percentile (" + p95 + " ms) exceeds the maximum allowed time of 3000 ms");
        }
    }

    protected void logStepWithScreenshot(String stepName, String description) {
        String path = ScreenshotUtil.takeScreenshot(driver, browserName, stepName);
        if (path != null) {
            test.info(description, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } else {
            test.info(description + " (Capture could not be taken)");
        }
    }

    protected void logPassWithScreenshot(String stepName, String description) {
        String path = ScreenshotUtil.takeScreenshot(driver, browserName, stepName);
        if (path != null) {
            test.pass(description, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } else {
            test.pass(description);
        }
    }
}