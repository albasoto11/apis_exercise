@login @regression
Feature: Login API - demoblaze.com Autenticación

  Background:
    * def baseUrl = 'https://api.demoblaze.com'
    * url baseUrl
    * def loginEndpoint  = '/login'
    * def signupEndpoint = '/signup'
    * def encode = function(s){ return java.util.Base64.getEncoder().encodeToString(s.getBytes()); }
    * def validUser     = 'Mabel Perez'
    * def validPassword = '123456'

  @smoke @positive
  Scenario: TC-LG-01 - Iniciar sesión con usuario y contraseña correcto
    Given path loginEndpoint
    And request { username: '#(validUser)', password: '#(validPassword)' }
    When method POST
    Then status 200
    And match response == '#string'
    * def token = response
    * print 'Longitud del token de autenticación:', token.length()
    And assert token.length() > 10

  @negative
  Scenario: TC-LG-02 - Login con la contraseña incorrecta
    * def wrongPassword = encode('wrongpassword')
    Given path loginEndpoint
    And request { username: '#(validUser)', password: '#(wrongPassword)' }
    When method POST
    Then status 200
    And match response != null
    And match response contains { errorMessage: '#string' }
    * print 'Error de contraseña incorrecta:', response

  @negative
  Scenario: TC-LG-03 - Iniciar sesión con un nombre de usuario inexistente
    * def ghostUser = 'ghost_' + java.lang.System.currentTimeMillis()
    * def encoded   = encode('anypassword')
    Given path loginEndpoint
    And request { username: 'Pameliasdsdsdsds', password: '#(encoded)' }
    When method POST
    Then status 200
    And match response != null
    And match response contains { errorMessage: '#string' }
    * print 'Error de usuario inexistente:', response

  @negative @boundary
  Scenario: TC-LG-04 - Iniciar sesión con credenciales vacías
    Given path loginEndpoint
    And request { username: '', password: '' }
    When method POST
    Then status 200
    And match response != null
    * print 'Respuesta de credenciales vacías:', response

  @smoke @positive @e2e
  Scenario: TC-LG-05 - E2E registro y login
    * def freshUser   = 'e2e_' + java.lang.System.currentTimeMillis()
    * def plainPass   = 'E2ePass99!'
    * def encodedPass = encode(plainPass)
    # Step 1 - Register
    Given path signupEndpoint
    And request { username: '#(freshUser)', password: '#(plainPass)' }
    When method POST
    Then status 200
    * print 'Usuario registrado:', freshUser
    # Step 2 - Login
    Given path loginEndpoint
    And request { username: '#(freshUser)', password: '#(plainPass)' }
    When method POST
    Then status 200
    And match response != null
    And assert response.length() > 10
    * print 'Token for fresh user:', response

# TC-LG-06 (Outline): Autenticar usuarios desde json
  @datadriven @json
  Scenario Outline: TC-LG-06 - Outline - Login JSON data-driven - <description>
    * def encodedPwd = encode('<password>')
    Given path loginEndpoint
    And request { username: '<username>', password: '<password>' }
    When method POST
    Then status 200
    * print 'JSON login [<description>]:', response

    Examples:
      | read('classpath:demoblaze/data/login-users.json') |
