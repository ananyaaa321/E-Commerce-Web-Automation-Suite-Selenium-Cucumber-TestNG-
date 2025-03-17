@test
Feature: Shopping Cart Functionality 

  Background: User logs in before accessing the shopping cart
   Given User is on the login page 
    And User logs in with "abc123@gmaill.com" and "abc123" and lands on the home page  

  Scenario: View shopping cart  
   When The user clicks on the shopping cart  
   Then The shopping cart page should be displayed
   
  Scenario: Remove a product from the shopping cart  
    Given The user has "14.1-inch Laptop" in the shopping cart with a quantity of "1"  
    When The user removes "14.1-inch Laptop "  
    Then "14.1-inch Laptop " should no longer be in the cart  

  Scenario: Update product quantity in the cart  
    Given The user has "Smartphone" in the shopping cart with a quantity of "1"  
    When The user changes the quantity to "2" and updates the cart  
    Then The cart should be updated accordingly  

  Scenario: Proceed to checkout  
    Given The user has "Smartphone" in the shopping cart with a quantity of "2"  
    When The user accepts Terms and conditions
    And The user clicks on the "Proceed to Checkout" button  
    Then The user should be taken to the checkout page  
