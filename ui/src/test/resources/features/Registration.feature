Feature: User Registration

  Scenario: Register a new user
    Given I am on the registration page
    When I enter my name and email address
    And I fill in the registration form
    And I submit the registration form
    Then I should see a confirmation message
