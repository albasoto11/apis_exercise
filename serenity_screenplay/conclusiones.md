
  # SERENITY BDD + SCREENPLAY — DEMOBLAZE E2E


## 1. DESCRIPCIÓN DEL EJERCICIO
Se automatizó el flujo E2E de compra en demoblaze.com cubriendo:
  • Navegación a la página principal
  • Selección y adición de 2 productos al carrito
  • Visualización del carrito
  • Llenado del formulario de compra (Place Order)
  • Confirmación exitosa de la compra

El patrón de diseño elegido fue Screenplay, sobre la base de Serenity BDD
con Cucumber 7 y JUnit 5, usando Gradle como herramienta de build.


## 2. HALLAZGOS TÉCNICOS DURANTE LA AUTOMATIZACIÓN
──────────────────────────────────────────────────────────────────────────────

### 2.1 ALERTA DE NAVEGADOR AL AGREGAR AL CARRITO
  - Al hacer clic en "Add to cart", demoblaze.com lanza un alert() nativo
    del navegador con el mensaje "Product added".
  - Este alert debe ser aceptado programáticamente antes de continuar con
    cualquier otra acción. De no hacerse, el WebDriver lanza
    UnhandledAlertException y la prueba falla.
  - **SOLUCIÓN**: Se creó la task AcceptAlertIfPresent con un breve sleep(1000)
    para esperar que el alert aparezca antes de intentar aceptarlo.

### 2.2 CARGA ASÍNCRONA DE PRODUCTOS
  - El catálogo de productos se carga de forma asíncrona mediante AJAX.
    Si se intenta hacer clic en un producto antes de que esté visible en el
    DOM, se obtiene un NoSuchElementException.
  - **SOLUCIÓN**: Se usa WaitUntil de Serenity Screenplay antes de cada
    interacción crítica (clic en producto, clic en "Add to cart",
    apertura del modal "Place Order").

### 2.3 NAVEGACIÓN DE REGRESO AL HOME DESPUÉS DE AGREGAR PRODUCTO
  - Después de agregar un producto al carrito, el sitio no regresa
    automáticamente al catálogo. Para agregar un segundo producto
    es necesario navegar explícitamente de vuelta al home.
  - SOLUCIÓN: La task AddProductToCart incluye un Open.url al finalizar.

### 2.4 INESTABILIDAD EN EL CARRITO
  → En ocasiones, después de agregar ambos productos y navegar al carrito,
    la tabla de ítems tarda algunos segundos en renderizarse completamente.
  → SOLUCIÓN: WaitUntil con timeout de 15 segundos sobre el botón
    "Place Order" como señal de que el carrito está listo.

### 2.5 MODAL "PLACE ORDER"
- El modal de compra se abre con animación Bootstrap. Los campos del
    formulario no son inmediatamente interactuables.
- **SOLUCIÓN**: WaitUntil sobre el campo "name" antes de empezar a escribir.


### 2.7 DATOS DE PRUEBA
  - Se implementaron dos mecanismos para el Scenario Outline:
    a) Tabla Examples directamente en el archivo .feature (Gherkin nativo)
    b) Archivo JSON en src/test/resources/testdata/ para consumo
  - Esto permite agregar nuevos casos sin modificar código Java.


## 3. OBSERVACIONES DE CALIDAD DEL SITIO (BUGS / DEBILIDADES)
──────────────────────────────────────────────────────────────────────────────

### 3.1 AUSENCIA DE VALIDACIONES EN EL FORMULARIO DE COMPRA
  - El formulario de "Place Order" acepta cualquier valor en los campos
    de tarjeta de crédito, mes y año, incluyendo texto arbitrario o campos
    vacíos. No hay validación de formato ni de campos requeridos.
  - IMPACTO: Un usuario podría completar una "compra" con datos ficticios.
    Esto es aceptable para un sitio de demostración pero sería un defecto
    crítico en producción.


## 6. CONCLUSIÓN FINAL

El flujo de compra de demoblaze.com se automatizó de forma robusta y
mantenible utilizando el patrón Screenplay de Serenity BDD.

Los principales desafíos técnicos (alerts nativos del navegador, carga
asíncrona, paginación del catálogo) fueron resueltos con estrategias de
espera explícita y manejo adecuado del DOM.



