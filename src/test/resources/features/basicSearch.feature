@basicsearch
Feature: Searching for a product  
Background: 
Given User is on the landing page  

Scenario Outline: Search for a valid product  
When User searches for "<Product>"  
Then The relevant products should be displayed  

    Examples:
      | Product    |
      | Laptop     |
      | Smartphone |

Scenario Outline: Search for an invalid product  
When User searches for "<InvalidProduct>"  
Then An error message should be displayed

    Examples:
      | InvalidProduct   |
      | xyz123           |
      | RandomItem       |
