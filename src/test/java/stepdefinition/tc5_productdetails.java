package stepdefinition;

import io.cucumber.java.en.*;
import pageObjects.ProductDetailsPage;
import pageObjects.SearchPage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class tc5_productdetails {
	WebDriver driver=Hooks.getDriver();
    ProductDetailsPage productDetailsPage;
    SearchPage searchPage;
    
    @Given("The user is on the product details page for {string}")
    public void the_user_is_on_the_product_details_page(String productName) {
        searchPage = new SearchPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        searchPage.enterSearchKeyword(productName);
        searchPage.clickSearchButton();
        searchPage.clickOnFirstProduct();
    }

    @When("The user views the product details")
    public void the_user_views_the_product_details() {
        Assert.assertNotNull(productDetailsPage, "Product details page is not loaded!");
    }

    @Then("The product title, description, price, and images should be visible")
    public void the_product_details_should_be_visible() {
        Assert.assertEquals(productDetailsPage.getProductTitle(), "14.1-inch Laptop", "Product title does not match!");
        Assert.assertTrue(productDetailsPage.isProductDescriptionVisible(), "Product description is not displayed!");
        Assert.assertTrue(productDetailsPage.isProductPriceVisible(), "Product price is not displayed!");
        Assert.assertTrue(productDetailsPage.isProductImagesVisible(), "Product images are not visible!");
    }

    @When("The user clicks on {string}")
    public void the_user_clicks_on(String button) {
        if (button.equalsIgnoreCase("Add to Cart")) {
            productDetailsPage.addToCart();
            Assert.assertTrue(productDetailsPage.isNotificationMessageDisplayed(), "Product was not added to cart!");
        } else if (button.equalsIgnoreCase("Add to Wishlist")) {
            boolean isWishlistAdded = productDetailsPage.addToWishlist();
            Assert.assertTrue(isWishlistAdded, "Wishlist button is missing or product was not added to wishlist!");
        }
    }

    @Then("{string} should be displayed in the shopping cart")
    public void product_should_be_displayed_in_cart(String productName) {
        productDetailsPage.goToShoppingCart();
    }

    @Then("{string} should be displayed in the wishlist")
    public void product_should_be_displayed_in_wishlist(String productName) {
        productDetailsPage.goToWishlist();
    }
}
