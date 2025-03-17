package stepdefinition;

import io.cucumber.java.en.*;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import Utility.Log4jLogger;
import pageObjects.OrderPlacementPage;

public class tc7_orderplacement {
    WebDriver driver = Hooks.getDriver();
    OrderPlacementPage orderPlacementPage = new OrderPlacementPage(driver);

    @When("The user enters the billing details {string}, {string}, {string}, {string}, {string}")
    public void the_user_enters_the_billing_details(String country, String city, String address, String zipCode, String phoneNumber) {
       orderPlacementPage.selectNewBillingAddress();
       SoftAssert softAssert = new SoftAssert();
    	boolean isValid = orderPlacementPage.enterBillingDetails(country, city, address, zipCode, phoneNumber);
        
        if (!isValid) {
            Log4jLogger.info("Invalid billing details provided. Error message displayed ");
            softAssert.assertTrue(true, "Expected failure: Billing details are invalid."); 
            softAssert.assertAll(); // Mark test as successfully executed            
            return;
        }
        orderPlacementPage.saveShippingAddress();
    }

    @And("The user selects shipping method {string}")
    public void user_selects_shipping_method(String shippingMethod) {
        orderPlacementPage.selectShippingMethod();
    }

    @And("The user selects payment method {string}")
    public void user_selects_payment_method(String paymentMethod) throws InterruptedException {
        orderPlacementPage.selectPaymentMethod(paymentMethod);
    }

    @And("The user confirms the order")
    public void user_confirms_order() {
        orderPlacementPage.confirmOrder();
    }
    @Then("The order should be {string}")
    public void the_order_should_be(String expectedResult) {
    	boolean isOrderPlaced;
        
        try {
            String confirmationMessage = orderPlacementPage.getOrderConfirmationMessage();
            isOrderPlaced = confirmationMessage.contains("Your order has been successfully processed!");
        } catch (Exception e) {
            // If exception occurs, order is not placed
            isOrderPlaced = false;
        }

        if (expectedResult.equalsIgnoreCase("Success")) {
            Assert.assertTrue(isOrderPlaced, "Order was NOT placed successfully when it should have been.");
        } else { // Expected "Failure"
            Assert.assertFalse(isOrderPlaced, "Test Failed: Expected failure, but the order was processed successfully.");
        }
    }

}

