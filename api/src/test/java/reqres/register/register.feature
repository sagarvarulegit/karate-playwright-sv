Feature: Register a new user

  Background:
    * url baseUrl
    * header Accept = 'application/json'

  Scenario: Create a new user with details from a file
    Given path '/register'
    And def requestBody = read('classpath:reqres/register/new-user.json')
    And request requestBody
    When method post
    Then status 200
    And match response.id == '#present'
    And match response.token == '#present'
