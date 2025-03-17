@test
Feature: User Registration on DemoWebShop

  Scenario Outline: User registers with various details
    Given User is on the registration page
    When User selects "<Gender>" gender
    And User enters "<FirstName>", "<LastName>", "<Email>", "<Password>", and "<ConfirmPassword>"
    And User clicks on the Register button
    Then Registration should be "<ExpectedResult>"

    Examples:
      | Gender | FirstName | LastName | Email              | Password  | ConfirmPassword | ExpectedResult |
      | Male   | Jay       | D        | Jayd123@gmall.com  | Test@1234 | Test@1234       | Success        |
      | Female | Jane      | Doe      | jannie@gmall.com   | Test@1234 | Test@1234       | Success        |
      | Male   | Alex      | Smith    | alex@gmaill.com    | Test@1234 | Test@12345      | Failure        |
      | Female |           |          | missing@example.com| Test@1234 |                 | Failure        | 
      | Male   | John      | Ray      | johnnn@gmaill.com  | Test@1234 | Test@1234       | Failure        |
      
      
      
      
      