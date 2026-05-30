# ============================================================
#  Feature : Signup API – https://api.demoblaze.com/signup
#  Author  : Alba Soto
#  Date    : 2026
# ============================================================
@signup @regression
Feature: Signup API – demoblaze.com

  Background:
    # baseUrl
    * url baseUrl = 'https://api.demoblaze.com'
    * def signupEndpoint = '/signup'

    # Helper: Generar usuario unico
    * def uniqueUser = 'user_' + java.lang.System.currentTimeMillis()

  # ----------------------------------------------------------
  # TC-SU-01: Happy path – crear nuevo usuario
  # ----------------------------------------------------------
  @smoke @positive
  Scenario: TC-SU-01 – Crear usuario nuevo correctamente
    Given path signupEndpoint
    And request { username: '#(uniqueUser)', password: '123456' }
    When method POST
    Then status 200
    # The API returns null body on success (no message field)
    And match response == '#string'
    * print 'Nuevo usuario registrado:', uniqueUser

  # ----------------------------------------------------------
  # TC-SU-02: Negativo –  #Intentar registrar un usuario duplicado
  # Esperado: API devuelve un mensaje de error en el cuerpo
  # ----------------------------------------------------------
  @negative
  Scenario: TC-SU-02 –  Intentar registrar un usuario duplicado
    Given path signupEndpoint
    And request { username: 'existing_test_user_karate', password: '123456' }
    When method POST
    Then status 200
    # The API replies with a non-null error message for duplicate usernames
    And match response != null
    And match response contains { errorMessage: 'This user already exist.' }
    * print 'Respuesta de error de usuario duplicado:', response

  # ----------------------------------------------------------
  # TC-SU-03: Negativo – Usuario vacio
  # ----------------------------------------------------------
  @negative @boundary
  Scenario: TC-SU-03 – Registrarse con un nombre de usuario vacío debería fallar
    Given path signupEndpoint
    And request { username: '', password: '123456' }
    When method POST
    Then status 200
    And match response != null
    * print 'Respuesta de nombre de usuario vacío:', response

  # ----------------------------------------------------------
  # TC-SU-04: Negativo – Contraseña vacia
  # ----------------------------------------------------------
  @negative @boundary
  Scenario: TC-SU-04 – El registro con una contraseña vacía debería fallar
    Given path signupEndpoint
    And request { username: '#(uniqueUser)', password: '' }
    When method POST
    Then status 200
    And match response != null
    * print 'Respuesta del API:', response

  # ----------------------------------------------------------
  # TC-SU-05 (Outline): Crear usuarios desde  CSV
  # ----------------------------------------------------------
  @datadriven
  Scenario Outline: TC-SU-05 – Outline – <description>
    Given path signupEndpoint
    And request { username: '<username>', password: '<password>' }
    When method POST
    Then status 200
    And match response <assertion>
    * print 'Datos registrados', '<username>', '| result:', response

    Examples:
      | read('classpath:demoblaze/data/signup-users.csv') |


