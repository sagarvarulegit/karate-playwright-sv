Feature: Register a new user

  Background:
    * url baseUrl
    * header Accept = 'application/json'
    * header x-api-key = apiKey

  Scenario: Create a new user with details from a file
    Given path '/register'
    And def requestBody = read('classpath:com/sagar/karate/demo/testdata/new-user.json')
    And request requestBody
    When method post
    Then status 200
    And match response.id == '#present'
    And match response.token == '#present'
