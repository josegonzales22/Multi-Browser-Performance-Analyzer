# 🚀 Multi-Browser-Performance-Analyzer

### Performance & Load Testing Template

Framework base para la medición de rendimiento web con ejecución multinavegador, soporte para ejecución local y remota, y generación automática de reportes HTML con métricas de tiempos de carga y dashboards.

Este proyecto sirve como template inicial para construir una arquitectura limpia y escalable de análisis de performance con Selenium WebDriver + JUnit 5 + Maven, especialmente enfocada en Single Page Applications (SPA).

---

## 📦 Características Principales

* ✔️ Multi-Browser: Ejecuta en Chrome, Edge, Firefox y Safari con un solo comando.
* ✔️ WebDriverManager: Descarga automática de drivers sin configuración manual.
* ✔️ SPA Friendly: Medición precisa de tiempos de carga incluso en aplicaciones de una sola página.
* ✔️ Métricas Avanzadas: Mínimo, Máximo, Mediana y Percentil 95 (P95).
* ✔️ Reportes Profesionales: Generación de archivos HTML mediante ExtentReports.
* ✔️ Ready for CI/CD: Compatible con Windows, Linux y macOS; soporte para RemoteWebDriver.
* ✔️ Umbrales: Configuración de umbrales de rendimiento (SLA) por página.

---

## 🧩 Tecnologías Usadas

| Tecnología | Versión | Uso |
|-----------|---------|-----|
| Selenium WebDriver | 4.25.0 | Automatización web |
| JUnit Jupiter | 5.11.0 | Estructura de tests |
| WebDriverManager | 5.9.2 | Gestión de drivers |
| ExtentReports | 5.1.1 | Reportes HTML |
| Jackson Databind | 2.18.0 | Manejo de JSON |
| Maven | - | Build Management |

---

## ▶️ Ejecución del Proyecto

Para ejecutar todos los tests de performance:

```bash
mvn clean test
```


## 🌐 Navegadores Soportados

| Navegador | Local | CI/CD (Remote) | Notas |
|:--- |:---:|:---:|:---|
| Chrome | ✔️ | ✔️ | Soporte total |
| Edge | ✔️ | ✔️ | Soporte total |
| Firefox | ✔️ | ✔️ | Soporte total |
| Safari | ✔️ | ✔️ | Local solo en macOS |

Nota: Safari y otros navegadores pueden ejecutarse en CI/CD mediante proveedores remotos como Selenium Grid, BrowserStack o LambdaTest.



## 📄 Reportes HTML (ExtentReports)

Los reportes se generan automáticamente en:

```
/reports/ExecutionReport_CrossBrowserSuite_<timestamp>.html
```

Métricas incluidas:
* Dashboard General de performance.
* Métricas de Tiempos por página.
* Estadísticas: Mínimo, Máximo, Mediana y Percentil 95.


## ⚙️ Configuración del Proyecto

* Java 17 y Maven.
* UTF-8 forzado para compatibilidad en logs.
* Perfiles Maven: Configurados para diferenciar entornos Windows y macOS (Safari).


## ⭐ Conclusión

Template moderno y robusto para QA Performance, ideal para monitoreo de tiempos de carga e integración continua en pipelines.

## Licencia

Este proyecto utiliza la [Licencia MIT](https://opensource.org/licenses/MIT).