package com.threebrowsers.selenium.pages;

import org.openqa.selenium.*;

public class NavigatePage extends BasePage {

    private final By menuButton = By.xpath("//button[.//text()[contains(., 'menu')]]");
    
    private final By componentButtonPage = By.xpath("(//a[contains(@href, '/components')])[1]");
    private final By titleComponentPage = By.xpath("//h1[contains(., 'Angular Material Dashboard Components')]");

    private final By formButtonPage = By.xpath("(//a[contains(@href, '/forms')])[1]");
    private final By titleFormPage = By.xpath("//h2[contains(., 'Forms')]");

    private final By contentButtonPage = By.xpath("(//a[contains(@href, '/content')])[1]");
    private final By titleContentPage = By.xpath("//h2[contains(., 'Browse your content')]");

    public NavigatePage(WebDriver driver) {
        super(driver);
    }

    public By mainPageLoadedLocator() {
        return menuButton;
    }

    public By componentPageLoadedLocator() {
        return titleComponentPage;
    }

    public By formPageLoadedLocator() {
        return titleFormPage;
    }
    
    public By contentPageLoadedLocator() {
        return titleContentPage;
    }

    public void clickMenu() {
        safeClick(menuButton);
        System.out.println("[INFO] Interacción con botón de menú");
    }

    public void goToComponentsPage() {
        safeClick(componentButtonPage);
        waitForElementToLoad(componentButtonPage, "Componentes");
    }

    public void goToFormsPage() {
        safeClick(formButtonPage);
        waitForElementToLoad(formButtonPage, "Formularios");
    }

    public void goToContentPage() {
        safeClick(contentButtonPage);
        waitForElementToLoad(contentButtonPage, "Contenido");
    }
}
