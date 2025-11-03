Feature: User Login

  @CreateUserFirst
  Scenario: Login with valid credentials
    Given I am on the login page
    When I enter the registered email and password
    And I click the login button
    Then I should be logged in successfully
