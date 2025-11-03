# This is a comment.
# This file contains our test scenarios for the 'user' API.

@smoke
Feature: User API Tests

  # The Background is executed before each Scenario in this file.
  Background:
    * url baseUrl
    * header Accept = 'application/json'
    * header x-api-key = apiKey

  Scenario: 1. Get a single user by ID

    # This is our first test case
    Given path '/users/2'
    When method get
    Then status 200

    # We can now assert on the JSON response
    # '$' represents the entire response body
    * print 'Response body is: ', response
    
    # Assert that the 'id' field inside the 'data' object is 2
    And match $.data.id == 2
    # Assert that the email is the one we expect
    And match $.data.email == 'janet.weaver@reqres.in'
    # We can also just check if a field is present
    And match $.data.first_name == '#present'
    And match $.data.last_name == 'Weaver'
