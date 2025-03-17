@test
Feature: User Login  
  
  Scenario: Login with credentials from an external data source  
    Given User is on the login page  
    When user enters the email and password from the excel file  
    Then user reaches home page or error message is displayed  
