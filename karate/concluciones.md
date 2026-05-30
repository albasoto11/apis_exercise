# CONCLUSIONES Y HALLAZGOS – Pruebas API demoblaze.com
 

## 1. COMPORTAMIENTO DE LA API DE SIGNUP

- : La API POST /signup devuelve HTTP 200 en TODOS los casos
(éxito y error), sin utilizar códigos de estado diferenciadores
(4xx / 5xx). La distinción se hace a través del cuerpo de respuesta:

- Registro exitoso  → body null o cadena vacía
- Usuario duplicado → { "errorMessage": "This user already exist." }
- Input inválido    → body con errorMessage o null según el caso

**Implicación para pruebas:**

Mejor práctica aplicada: Todos los escenarios validan tanto el
status code (200) como el contenido del body usando match de Karate.


## 2. COMPORTAMIENTO DE LA API DE LOGIN

- La API POST /login siempre devuelve
HTTP 200. La diferencia entre éxito y fallo es:

- Login exitoso          → Token de autenticación (string largo)
- Credenciales inválidas → { "errorMessage": "Wrong password." }
  o { "errorMessage": "User does not exist." }




## 3. CODIFICACIÓN DE CONTRASEÑAS

La API de login espera la contraseña codificada en Base64. Esto fue
identificado analizando las peticiones del sitio web demoblaze.com.
Se implementó una función de codificación reutilizable en el Background
de login.feature para aplicar DRY (Don't Repeat Yourself).


# 4. MEJORES PRÁCTICAS APLICADAS
--------------------------------
 ### a) karate-config.js centralizado:
- Un único punto de configuración para baseUrl, timeouts y headers.
- Soporte multi-entorno (-Dkarate.env=dev|staging).

### b) Escenarios con tags:
- @smoke: subset de alta prioridad para ejecución rápida en CI/CD.
- @positive / @negative: clasificación por tipo de validación.
- @datadriven: escenarios parametrizados.
- @regression: suite completa.

### c) Datos externalizados (CSV y JSON):
- Los archivos de datos están en /data/ separados del código de prueba.
- Permiten agregar nuevos casos de prueba sin modificar los .feature.

### d) Scenario Outline con CSV y JSON:
- Karate lee nativamente ambos formatos con read('classpath:...')
- Cada fila define sus propios datos de entrada Y aserciones esperadas.

### e) Usuarios únicos con timestamp:
- user_<epochMs> evita fallos por colisión de datos entre ejecuciones
  en entornos compartidos o en ejecuciones paralelas.


### f) Prueba E2E (TC-LG-05):
- Valida el flujo completo: registro → login en un único escenario,
  garantizando la integración entre ambas APIs.


## 5. LIMITACIONES IDENTIFICADAS

a) La API no implementa códigos HTTP REST estándar (siempre 200).
En una API bien diseñada se esperaría:
  - 201 Created para signup exitoso
  - 409 Conflict para usuario duplicado
  - 401 Unauthorized para login fallido

b) No hay endpoint de DELETE/cleanup: no es posible eliminar usuarios
creados durante las pruebas, lo que puede causar acumulación de
datos de prueba en la base de datos de demoblaze.

c) La API no valida formatos de contraseña ni tiene políticas de
complejidad, lo cual representa un riesgo de seguridad.

d) No existe un endpoint de health-check (/ping, /health) para
verificar disponibilidad del servicio antes de las pruebas.


## 6. RECOMENDACIONES

a) Implementar un usuario de prueba fijo pre-seeded en el ambiente
para el escenario de login válido, evitando dependencia del
escenario de signup.



b) Integrar en pipeline CI/CD (GitHub Actions, Jenkins, GitLab CI)
usando el reporte JUnit XML generado en build/test-results/.

d) Considerar usar un ambiente de staging separado para evitar
contaminar datos de producción con usuarios de prueba.


## 7. RESUMEN DE COBERTURA

Total de escenarios implementados: 14
- Signup: 5 escenarios (4 directos + 1 data-driven)
- Login : 6 escenarios (5 directos + 1 data-driven)
- Helpers reutilizables: 2 callable scenarios

Tipos de validación:
- Status code HTTP
- Estructura del body (match)
- Contenido de campos específicos (errorMessage, token)
- Longitud y tipo de respuesta
- Casos positivos y negativos
- Casos límite (boundary: campos vacíos)
- Flujo end-to-end (signup → login)
- Datos parametrizados CSV y JSON

