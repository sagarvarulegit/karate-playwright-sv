@smoke
Feature: User API Tests

  Background:
    * url baseUrl
    * header Accept = 'application/json'

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

  Scenario: Get list of users and then get details for each user
    Given path '/users'
    When method get
    Then status 200
    * def users = response.data
    * call read('classpath:reqres/users/user-details.feature@get-user') users