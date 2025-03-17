@test
Feature: Product Details Validation  

  Background: User logs in before accessing product details  
    Given User is on the login page 
    And User logs in with "test0123@example.com" and "Test123" and lands on the home page  

  Scenario: Verify product details are displayed correctly  
    Given The user is on the product details page for "Laptop"  
    When The user views the product details  
    Then The product title, description, price, and images should be visible  

  Scenario: Add product to cart from product details page  
    Given The user is on the product details page for "Laptop "  
    When The user clicks on "Add to Cart"  
    Then "14.1-inch Laptop" should be displayed in the shopping cart  

  Scenario: Add product to wishlist from product details page  
    Given The user is on the product details page for "Laptop "  
    When The user clicks on "Add to Wishlist"  
    Then "14.1-inch Laptop" should be displayed in the wishlist  
