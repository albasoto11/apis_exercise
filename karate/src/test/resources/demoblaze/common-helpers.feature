# ============================================================
#  Reusable Feature: common-helpers.feature
#  Contains callable scenarios used by signup and login features
#  to avoid code duplication (DRY principle).
# ============================================================
@ignore
Feature: Common reusable API helpers

  # ── Register a new user (called from other features) ──────
  @ignore
  Scenario: register-user
    # Expected input: { username, password }
    Given url baseUrl
    And path '/signup'
    And request { username: '#(username)', password: '#(password)' }
    When method POST
    Then status 200
    * def signupResult = response

  # ── Login and capture token ──────────────────────────────
  @ignore
  Scenario: login-and-get-token
    # Expected input: { username, encodedPassword }
    Given url baseUrl
    And path '/login'
    And request { username: '#(username)', password: '#(encodedPassword)' }
    When method POST
    Then status 200
    * def authToken = response
