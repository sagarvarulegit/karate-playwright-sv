Feature: Home Page

  Scenario: Check home page title
    Given I am on the Playwright home page
    When I check the title of the page
    Then the title should be "Fast and reliable end-to-end testing for modern web apps | Playwright"
