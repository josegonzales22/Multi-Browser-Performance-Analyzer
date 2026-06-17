# 🚀 Selenium Cross Browser – Automation Template

Framework base para **pruebas funcionales automatizadas** con ejecución **multinavegador**, soporte para ejecución *
*local y remota**, y generación automática de **reportes HTML jerárquicos** con dashboard.

Este proyecto sirve como *template* inicial para construir una arquitectura limpia, polimórfica y escalable de
automatización utilizando **Selenium WebDriver + JUnit 5 + Maven + AspectJ**, soportando ejecución modular en:

- **Chrome**
- **Edge**
- **Firefox**
- **Safari** *(local en macOS o remoto vía RemoteWebDriver)*
- **Remote WebDriver** (Selenium Grid, Selenoid, BrowserStack, LambdaTest, etc.)

## 📦 Características Principales

- ✔️ **Arquitectura Orientada a Flujos (Step-Facade):** Desacoplamiento total entre la infraestructura de navegadores (
  tests) y la lógica de negocio (steps). Permite registrar múltiples flujos de prueba sin duplicar configuraciones.
- ✔️ **Reportes Jerárquicos Estructurados:** Agrupación visual en ExtentReports por Caso de Prueba Principal en la raíz
  izquierda, y desgloses expandibles por cada Navegador (Nodos Hijos) en el panel central.
- ✔️ **Capturas de Pantalla Inline Unificadas:** Integración directa mediante MediaEntityBuilder para incrustar la
  evidencia fotográfica exactamente debajo del paso correspondiente en una sola línea de log.
- ✔️ **Manejo de Drivers Nativo:** Uso de las capacidades nativas de Selenium 4 para el aprovisionamiento y gestión de
  binarios de los navegadores.
- ✔️ **Inyección Dinámica de Flujos:** Uso de Lambdas e Interfases Funcionales de Java (TestFlowExecutor) para inyectar
  dinámicamente cualquier lógica de pasos en los drivers creados por la suite.
- ✔️ **Filtrado Flexible (CLI):** Ejecución total de la suite de pruebas o selectiva por etiquetas mediante el uso
  nativo de propiedades de Surefire.
- ✔️ **Codificación Forzada:** Configuración UTF-8 a nivel de JVM y reportes para asegurar compatibilidad
  multiplataforma en servidores CI/CD.

## 🧩 Tecnologías Usadas

| Tecnología         | Versión  | Uso                                                  |
|--------------------|----------|------------------------------------------------------|
| Selenium WebDriver | 4.25.0   | Automatización de interacciones del navegador        |
| JUnit Jupiter      | 5.11.0   | Orquestación, ciclo de vida y aserciones de pruebas  |
| ExtentReports      | 5.1.1    | Motor de reportería HTML con reporter Spark          |
| AspectJ Weaver     | 1.9.25.1 | Intercepción en tiempo de ejecución para anotaciones |
| Jackson Databind   | 2.18.0   | Serialización y mapeo de configuraciones             |
| Apache Commons IO  | 2.16.1   | Manipulación avanzada de archivos y directorios      |
| Log4j Core / Api   | 2.23.1   | Registro de trazas de ejecución en consola (Logs)    |
| Maven              | 3.x      | Ciclo de vida del build y gestor de dependencias     |

## 📂 Estructura del Framework

El proyecto distribuye de manera limpia los flujos lógicos de los componentes de infraestructura:

```
├── 📁 src
│   ├── 📁 main
│   │   ├── 📁 java
│   │   │   └── 📁 com
│   │   │       └── 📁 threebrowsers
│   │   │           └── 📁 selenium
│   │   │               ├── 📁 drivers
│   │   │               ├── 📁 images
│   │   │               ├── 📁 pages
│   │   │               ├── 📁 reports
│   │   │               ├── 📁 steps
│   │   │               └── 📁 utils
│   │   └── 📁 resources
│   └── 📁 test
│       ├── 📁 java
│       │   ├── 📁 annotations
│       │   └── 📁 com
│       │       └── 📁 threebrowsers
│       │           └── 📁 selenium
│       │               ├── 📁 infrastructure
│       │               └── 📁 tests
│       └── 📁 resources
```

## ▶️ Ejecución del Proyecto

Puedes controlar la cobertura del set de pruebas directamente desde la terminal del sistema:

### 🔹 1. Ejecución Total Masiva

Ejecuta de forma secuencial todas las clases de suite registradas en la carpeta de pruebas recorriendo cada uno de los
navegadores configurados internamente:

```bash
mvn clean test
```

### 🔹 2. Ejecución Selectiva por Navegadores

Filtra la ejecución en base a las anotaciones personalizadas de tu entorno (como @ChromeDesktop, @EdgeDesktop, etc.)
gracias al mapeo de propiedades dinámicas:

```bash
mvn clean test -Dgroups="chrome_desktop,edge_desktop"
```

## 🌐 Matrices de Navegación Soportadas

### Ejecución Local

| Navegador | OS Soportados         | Notas                                          |
|-----------|-----------------------|------------------------------------------------|
| Chrome    | Windows, Linux, macOS | Inicialización local headless configurable.    |
| Edge      | Windows, Linux, macOS | Inicialización local headless configurable.    |
| Firefox   | Windows, Linux, macOS | Inicialización local con soporte gecko driver. |
| Safari    | Solo macOS            | Ejecución nativa mediante SafariDriver local.  |

### Ejecución Remota (Grid / Clouds)

Cualquier suite puede ser derivada a infraestructuras distribuidas configurando las credenciales u endpoints en los
archivos .properties:

- Selenium Grid / Selenoid
- BrowserStack / LambdaTest / SauceLabs

## 📄 Estructura de Reportes HTML (ExtentReports)

Los reportes consolidados limpian y recrean sus artefactos en cada ciclo completo bajo la ruta:
/reports/ExecutionReport_CrossBrowserSuite_<timestamp>.html

### Visualización del Reporte

1. Panel Izquierdo: Listado limpio indexado por el nombre del archivo de la Suite (ej: Login Suite Test).
2. Panel Central (Nodos Hijos): Bloques colapsables independientes para CHROME, EDGE, FIREFOX, etc.
3. Flujo de Pasos Inline: Cada paso ejecutado muestra la descripción de la acción (Info o Pass) con la captura de
   pantalla incrustada de forma contigua en la misma línea, reduciendo el ruido visual en fallas.

## Licencia

Este proyecto se distribuye bajo los términos de la Licencia MIT (https://opensource.org/licenses/MIT).

## Disclaimer

La aplicación web utilizada en los ejemplos de este proyecto (angular-dashboard-lime.vercel.app) pertenece a Zoaib
Khan (https://www.youtube.com/@ZoaibKhan). Se utiliza exclusivamente con fines educativos, demostrativos y para
prácticas avanzadas de automatización.