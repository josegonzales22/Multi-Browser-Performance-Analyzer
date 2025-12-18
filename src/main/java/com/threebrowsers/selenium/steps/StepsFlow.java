package com.threebrowsers.selenium.steps;

import com.threebrowsers.selenium.pages.*;
import com.threebrowsers.selenium.utils.PerformanceUtil;
import com.threebrowsers.selenium.utils.MetricsUtil;
import org.openqa.selenium.WebDriver;
import com.threebrowsers.selenium.images.ScreenshotUtil;
import com.aventstack.extentreports.ExtentTest;

import java.util.Set;

public class StepsFlow {

    private final WebDriver driver;
    private final String browserName;
    private final LoginPage loginPage;
    private final NavigatePage navigatePage;
    private final ExtentTest test;

    public StepsFlow(WebDriver driver, String browserName, ExtentTest test) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
        this.navigatePage = new NavigatePage(driver);
        this.browserName = browserName;
        this.test = test;
    }

    public void executeFlow(String baseUrl) throws InterruptedException {
        String username = "zoaib@zoaibkhan.com";
        String psw = "testing123";
        ScreenshotUtil.resetCounter(browserName);

        test.info("Cargando página de login");
        long tiempoLogin = PerformanceUtil.measurePageLoad(
            driver,
            () -> loginPage.loadPage(baseUrl),
            loginPage.loginPageLoadedLocator()
        );
        MetricsUtil.addTime(tiempoLogin);

        test.info("⏱ Tiempo de carga LOGIN: " + tiempoLogin + " ms");
        ScreenshotUtil.takeScreenshot(driver, browserName, "loginPage_loaded");

        test.info("Ingresando nombre de usuario");
        loginPage.enterUsername(username);
        Thread.sleep(1000);
        ScreenshotUtil.takeScreenshot(driver, browserName, "username_entered");

        test.info("Ingresando contraseña");
        loginPage.enterPassword(psw);
        Thread.sleep(1000);
        ScreenshotUtil.takeScreenshot(driver, browserName, "password_entered");

        test.info("Haciendo clic en login");
        loginPage.clickLogin();
        Thread.sleep(2000);
        ScreenshotUtil.takeScreenshot(driver, browserName, "login_clicked");

        test.info("Cerrando menu");
        long tiempoMainPage = PerformanceUtil.measurePageLoad(
            driver,
            () -> navigatePage.clickMenu(),
            navigatePage.mainPageLoadedLocator()
        );
        test.info("⏱ Tiempo de carga PÁGINA PRINCIPAL: " + tiempoMainPage + " ms");
        MetricsUtil.addTime(tiempoMainPage);
        ScreenshotUtil.takeScreenshot(driver, browserName, "menu_closed");

        test.info("Ingresando a página de componentes");
        long tiempoComponentsPage = PerformanceUtil.measurePageLoad(
            driver,
            () -> navigatePage.goToComponentsPage(),
            navigatePage.componentPageLoadedLocator()
        );
        test.info("⏱ Tiempo de carga PÁGINA COMPONENTES: " + tiempoComponentsPage + " ms");
        MetricsUtil.addTime(tiempoComponentsPage);
        ScreenshotUtil.takeScreenshot(driver, browserName, "components_page");

        test.info("Ingresando a página de formulario");
        long tiempoFormsPage = PerformanceUtil.measurePageLoad(
            driver,
            () -> navigatePage.goToFormsPage(),
            navigatePage.formPageLoadedLocator()
        );
        test.info("⏱ Tiempo de carga PÁGINA FORMULARIOS: " + tiempoFormsPage + " ms");
        MetricsUtil.addTime(tiempoFormsPage);
        ScreenshotUtil.takeScreenshot(driver, browserName, "forms_page");

        test.info("Ingresando a página de contenido");

        long tiempoContentPage = PerformanceUtil.measurePageLoad(
            driver,
            () -> navigatePage.goToContentPage(),
            navigatePage.contentPageLoadedLocator()
        );
        test.info("⏱ Tiempo de carga PÁGINA CONTENIDO: " + tiempoContentPage + " ms");
        MetricsUtil.addTime(tiempoContentPage);
        ScreenshotUtil.takeScreenshot(driver, browserName, "content_page");

        double p95 = MetricsUtil.getP95();
        long min = MetricsUtil.getMin();
        long max = MetricsUtil.getMax();
        double median = MetricsUtil.getMedian();
        test.info("📊 Mínimo de los tiempos: " + min + " ms");
        test.info("📊 Máximo de los tiempos: " + max + " ms");
        test.info("📊 Mediana de los tiempos: " + median + " ms");
        test.info("📊 Percentil 95 de los tiempos: " + p95 + " ms");
        if (p95 > 3000) {
            test.fail("❌ El Percentil 95 (" + p95 + " ms) excede el límite de 3000 ms");
            throw new AssertionError("El P95 excede el tiempo máximo permitido (3000 ms)");
        }
        test.pass("Flujo completado exitosamente en " + browserName.toUpperCase());
    }
}
