package com.threebrowsers.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PerformanceUtil {

    /**
     * Mide el tiempo de carga de una nueva página o vista dentro de un SPA.
     *
     * @param driver             WebDriver actual
     * @param navigationAction   Acción que dispara el cambio de pantalla (click, selección de menú, etc.)
     * @param pageLoadedLocator  Elemento que indica que la vista está completamente cargada
     * @return tiempo en milisegundos que demoró la carga de dicha pantalla
     */
    public static long measurePageLoad(WebDriver driver, Runnable navigationAction, By pageLoadedLocator) {
        long startTime = System.currentTimeMillis();

        // Acción que inicia la navegación
        navigationAction.run();

        // Esperar a la carga completa de la vista
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageLoadedLocator));

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
