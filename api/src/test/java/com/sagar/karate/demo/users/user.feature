@smoke
Feature: User API Tests


  Background:
    * url baseUrl
    * header Accept = 'application/json'
    * header x-api-key = apiKey

  Scenario: Get a single user by ID

    Given path '/users/2'
    When method get
    Then status 200
    * print 'Response body is: ', response
    
    And match $.data.id == 2
    And match $.data.email == 'janet.weaver@reqres.in'
    And match $.data.first_name == '#present'
    And match $.data.last_name == 'Weaver'

  Scenario: Get user 3
    Given path '/users/3'
    When method get
    Then status 200
    And match $.data.id == 3

  Scenario: Compare user schemas
    * def userSchema = read('classpath:com/sagar/karate/demo/user-schema.json')

    Given path '/users/2'
    When method get
    Then status 200
    And match response == userSchema

    * path '/users/3'
    When method get
    Then status 200
    And match response == userSchema

  Scenario: Call user 2 twice and compare responses
    Given path '/users/2'
    When method get
    Then status 200
    * def response1 = response

    * path '/users/2'
    When method get
    Then status 200
    * def response2 = response

    And match response1 == response2