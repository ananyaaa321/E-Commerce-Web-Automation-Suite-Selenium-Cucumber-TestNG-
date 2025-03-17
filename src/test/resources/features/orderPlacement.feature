@test01
Feature: Order Placement

  Background: User logs in before accessing the shopping cart
    Given User is on the login page 
    And User logs in with "abc123@gmaill.com" and "abc123" and lands on the home page  

  Scenario Outline: Order Placement with different payment methods
  Given The user is on the product details page for "14.1-inch Laptop"
  When The user clicks on "Add to Cart"
  And The user clicks on the shopping cart
  When The user accepts Terms and conditions
  And The user clicks on the "Proceed to Checkout" button
  And The user enters the billing details "<Country>", "<City>", "<Address>", "<ZipCode>", "<PhoneNumber>"  
  And The user selects shipping method "Ground"
  And The user selects payment method "<PaymentMethod>"
  And The user confirms the order
  Then The order should be "<ExpectedResult>"
   Examples:
    | Country | City     | Address      | ZipCode  | PhoneNumber | PaymentMethod       | ExpectedResult |
    | Canada  | London   | 45B Baker St | SW1A 1AA | 9876543210  |Check / Money Order (5.00) | Success        |  
 	  | Canada  |          |              |          |             | Cash on Delivery    | Failure        |
    | Canada  | New York | 123 St, NY   | 10001    | 1234567890  | Cash on Delivery    | Success        |
    | Bahrain | NY       | 123 St       | ABC123   | XYZ12345    | Cash on Delivery    | Failure        |
	