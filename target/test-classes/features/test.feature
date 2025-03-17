@test001
Feature: verify login functionality

  Scenario: Verify valid login
    Given User is in login page
    When User enters credentials
    Then User should see welcome page
