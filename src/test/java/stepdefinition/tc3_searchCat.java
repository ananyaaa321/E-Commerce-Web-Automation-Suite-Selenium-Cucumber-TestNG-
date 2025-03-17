package stepdefinition;


import io.cucumber.java.en.*;
import pageObjects.SearchPage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class tc3_searchCat {
    WebDriver driver = Hooks.getDriver();
    SearchPage searchPage = new SearchPage(driver);
    
    @Given("User is on the landing page")
    public void user_is_on_the_landing_page() {
        driver.get("https://demowebshop.tricentis.com/");
    }

    @When("User searches for {string}")
    public void user_searches_for(String product) {
        searchPage.enterSearchKeyword(product);
        searchPage.clickSearchButton();
    }

    @Then("The relevant products should be displayed")
    public void the_relevant_products_should_be_displayed() {
        Assert.assertTrue(searchPage.isProductListDisplayed(), "Relevant products are not displayed");
    }

    @Then("an error message should be displayed")
    public void an_error_message_should_be_displayed() {
        String expectedMessage = "No products were found that matched your criteria.";
        Assert.assertEquals(searchPage.getNoResultsMessage(), expectedMessage, "Error message mismatch");
    }

    @When("User selects sorting option {string}")
    public void user_selects_sorting_option(String sortingType) {
        searchPage.selectSortingOption(sortingType);
    }

    @Then("The products should be displayed in the selected order")
    public void the_products_should_be_displayed_in_the_selected_order() {
        Assert.assertTrue(searchPage.isProductListDisplayed(), "Products are not sorted correctly");
    }

    @When("User selects display option {string}")
    public void user_selects_display_option(String displayCount) {
        searchPage.selectDisplayOption(displayCount);
    }

    @Then("The relevant products should be displayed accordingly")
    public void the_relevant_products_should_be_displayed_accordingly() {
        Assert.assertTrue(searchPage.isProductListDisplayed(), "Display count not applied correctly");
    }

    @When("User enables advanced search")
    public void user_enables_advanced_search() {
        searchPage.enableAdvancedSearch();
    }

    @When("User enables subcategory search")
    public void user_enables_subcategory_search() throws InterruptedException {
        searchPage.enableSubCategorySearch();
        Thread.sleep(4000);
        
    }
    @When("User clicks the advsearch button")
    public void user_clicks_advsearch_button() {
        searchPage.clickAdvSearchbtn();
    }
}
