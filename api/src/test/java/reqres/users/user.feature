@smoke
Feature: User API Tests

  Background:
    * url baseUrl
    * header Accept = 'application/json'

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
    * def userSchema = read('classpath:reqres/users/user-schema.json')
    Given path '/users/2'
    When method get
    Then status 200
    And match response == userSchema
    Given path '/users/3'
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

    Scenario Outline: Validate all the users from YAML
    Given path '/users/<id>'
    When method get
    Then status 200

    Examples:
      | read('classpath:reqres/users/user-ids.yaml').user|


      
  @user3
  Scenario Outline: Validate users.id 3 from YAML
    Given path '/users/<id>'
    When method get
    Then status 200

    Examples:
      | karate.filter(read('classpath:reqres/users/user-ids.yaml').user, function(x){ return x.id == 3 }) |

  Scenario Outline: List users with pagination filters
    Given path '/users'
    And param page = <page>
    And param per_page = <per_page>
    When method get
    Then status 200
    And match response.page == <page>
    And match response.per_page == <per_page>
    And match response.data == '#[<expected_count>]'

    Examples:
      | read('classpath:reqres/users/user-pagination.yaml') |

  
  Scenario: Get a user from the list and then get their details
    Given path '/users'
    When method get
    Then status 200
    * def firstUser = response.data[0]
    * def userId = firstUser.id
    Given path '/users', userId
    When method get
    Then status 200
    And match response.data.id == userId