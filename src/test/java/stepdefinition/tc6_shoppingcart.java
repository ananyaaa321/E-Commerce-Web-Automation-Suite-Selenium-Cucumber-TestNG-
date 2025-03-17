package stepdefinition;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pageObjects.*;
import pageObjects.ShoppingCartPage;
import org.openqa.selenium.WebDriver;

public class tc6_shoppingcart {
    WebDriver driver = Hooks.getDriver();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
    ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
    SearchPage searchPage = new SearchPage(driver);

    @When("The user clicks on the shopping cart")
    public void the_user_clicks_on_the_shopping_cart() {
        shoppingCartPage.openShoppingCart();
    }

    @Then("The shopping cart page should be displayed")
    public void the_shopping_cart_page_should_be_displayed() {
        Assert.assertTrue(shoppingCartPage.isShoppingCartPageDisplayed(), "Shopping cart page was not displayed!");
    }

    @Given("The user has {string} in the shopping cart with a quantity of {string}")
    public void user_has_product_in_cart(String productName, String quantity) {
        searchPage.enterSearchKeyword(productName);
        searchPage.clickSearchButton();
        searchPage.clickOnFirstProduct();
        productDetailsPage.addToCart();
        shoppingCartPage.openShoppingCart();
        Assert.assertTrue(shoppingCartPage.isProductInCart(productName), productName + " is not found in the cart!");
    }

    @When("The user removes {string}")
    public void user_removes_product(String productName) {
        shoppingCartPage.openShoppingCart();
        shoppingCartPage.removeProduct();
    }

    @Then("{string} should no longer be in the cart")
    public void product_should_not_be_in_cart(String productName) {
        Assert.assertFalse(shoppingCartPage.isProductInCart(productName), productName + " was not removed from the cart!");
    }

    @When("The user changes the quantity to {string} and updates the cart")
    public void user_updates_product_quantity(String newQuantity) {
        shoppingCartPage.openShoppingCart();
        shoppingCartPage.updateProductQuantity(newQuantity);
    }

    @Then("The cart should be updated accordingly")
    public void the_cart_should_be_updated_accordingly() {
        Assert.assertTrue(shoppingCartPage.isTotalPriceUpdated(), "Total price did not update correctly!");
    }

    @When("The user accepts Terms and conditions")
    public void the_user_accepts_terms_and_conditions() {
        shoppingCartPage.acceptTermsAndConditions();
    }

    @When("The user clicks on the {string} button")
    public void user_clicks_button(String buttonName) {
        if (buttonName.equalsIgnoreCase("Proceed to Checkout")) {
            shoppingCartPage.proceedToCheckout();
        }
    }

    @Then("The user should be taken to the checkout page")
    public void user_should_be_redirected_to_checkout() {
        Assert.assertTrue(shoppingCartPage.isCheckoutPageDisplayed(), "Checkout page was not displayed!");
    }
}
