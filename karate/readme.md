
## DESCRIPCIÓN

Este proyecto contiene pruebas automatizadas de APIs REST para los
servicios de registro (signup) y autenticación (login) de **demoblaze.com**,
implementadas con el framework Karate DSL y ejecutadas con Gradle.

APIs bajo prueba:
- **Signup**: POST https://api.demoblaze.com/signup
- **Login**: POST https://api.demoblaze.com/login


## PRE-REQUISITOS

1. Java 11 o superior instalado.
   Verificar: java -version
2. Gradle 7.6.6+ instalado  *** O usar el Gradle Wrapper incluido (recomendado). 
3. Conexión a internet activa (las pruebas llaman a la API real de demoblaze).


## ESTRUCTURA DEL PROYECTO
-----------------------
karate-demoblaze/
├── build.gradle                          Configuración de Gradle y dependencias
├── settings.gradle                       Nombre del proyecto
├── gradle/wrapper/                       Gradle Wrapper (ejecutar sin instalación)
├── src/
│   └── test/
│       ├── java/
│       │   └── DemoblazeRunner.java  JUnit 5 Runner – punto de entrada
│       │       
│       └── resources/
│           ├── karate-config.js          Configuración global (baseUrl, headers, env)
│           └── demoblaze/
│               ├── signup.feature        Escenarios de registro de usuarios
│               ├── login.feature         Escenarios de autenticación
│               ├── common-helpers.feature Pasos reutilizables (DRY)
│               └── data/
│                   ├── signup-users.csv  Datos para Scenario Outline – signup
│                   ├── login-users.json  Datos JSON para Scenario Outline – login
│                   └── 





## OPCIONES DE EJECUCIÓN
--------------------------------
### Ejecutar por tag (ej. solo smoke tests):
./gradlew test -Dkarate.options="--tags @smoke"

### Ejecutar por entorno:
./gradlew test -Dkarate.env=staging

### Ejecución paralela (4 threads):
./gradlew test -Dkarate.options="--threads 4"

### Ver output detallado en consola:
./gradlew test --info


## REPORTES

- Tras la ejecución, los reportes se generan automáticamente en:

- HTML (Karate nativo):build/karate-reports/karate-summary.html
→ Abrir en cualquier navegador web.

- XML (JUnit – compatible con CI/CD): build/test-results/test/*.xml

- JSON (Cucumber – compatible con Cucumber Reports):
build/karate-reports/*.json

Para abrir el reporte HTML directamente (macOS):
open build\karate-reports\karate-summary.html

Para abrir el reporte HTML directamente (Windows):
start build\karate-reports\karate-summary.html


## SCENARIOS CUBIERTOS
--------------------
### SIGNUP (signup.feature):
- TC-SU-01: Crear un nuevo usuario exitosamente           [smoke, positive]
- TC-SU-02: Intentar crear un usuario ya existente        [negative]
- TC-SU-03: Registrar con username vacío                  [negative, boundary]
- TC-SU-04: Registrar con password vacío                  [negative, boundary]
- TC-SU-05: Data-Driven con CSV (3 filas)                 [datadriven]

### LOGIN (login.feature):
- TC-LG-01: Login con credenciales correctas              [smoke, positive]
- TC-LG-02: Login con password incorrecto                 [negative]
- TC-LG-03: Login con usuario inexistente                 [negative]
- TC-LG-04: Login con credenciales vacías                 [negative, boundary]
- TC-LG-05: End-to-end signup → login                    [smoke, positive, e2e]
- TC-LG-06: Data-Driven con JSON (3 registros)            [datadriven, json]


### NOTAS IMPORTANTES

* El archivo karate-config.js centraliza la baseUrl y headers; si la URL
  de la API cambia, SOLO se modifica en ese archivo.

* Los usuarios nuevos se generan con timestamp para evitar colisiones entre
  ejecuciones

* Los archivos CSV y JSON en src/test/resources/demoblaze/data/ pueden
  editarse para agregar más casos sin tocar el código de los features.

* El test TC-SU-02 (usuario duplicado) primero crea el usuario y luego
  intenta crearlo de nuevo, garantizando que la precondición se cumpla
  en cualquier entorno limpio.




