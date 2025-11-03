Feature: User Registration

  Scenario: Register a new user
    Given I am on the registration page
    When I enter my name and email address
    And I fill in the registration form
    And I submit the registration form
    Then I should see a confirmation message

  Scenario: Login with registered user
    Given I am on the registration page
    When I enter my name and email address
    And I fill in the registration form
    And I submit the registration form
    Then I should see a confirmation message
    When I click continue button
    And I logout from the application
    And I am on the login page
    And I enter the registered email and password
    And I click the login button
    Then I should be logged in successfully
