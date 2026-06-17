package com.threebrowsers.selenium.steps;

import com.aventstack.extentreports.ExtentTest;
import com.threebrowsers.selenium.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginSteps extends BaseSteps {
    private final LoginPage loginPage;

    public LoginSteps(WebDriver driver, String browserName, ExtentTest test) {
        super(driver, browserName, test);
        this.loginPage = new LoginPage(driver);
    }

    public void execute(String baseUrl) throws InterruptedException {
        measureAndLogPageLoad(
                "loginPage_loaded",
                "Login page loaded and displayed",
                () -> loginPage.loadPage(baseUrl),
                loginPage.loginPageLoadedLocator()
        );

        loginPage.enterUsername("zoaib@zoaibkhan.com");
        Thread.sleep(1000);
        loginPage.enterPassword("testing123");
        Thread.sleep(1000);
        loginPage.clickLogin();
        Thread.sleep(2000);

        assertAndLogPerformanceMetrics();

        logPassWithScreenshot("login_completed", "Full business and performance scenario passed in " + browserName.toUpperCase());
    }
}