/*
 * package stepdefinition;
 * 
 * import io.cucumber.java.en.*; import org.openqa.selenium.WebDriver; import
 * org.testng.Assert; import pageObjects.LandingPage; import
 * pageObjects.SearchResultsPage;
 * 
 * public class tc3_searching { WebDriver driver = Hooks.getDriver();
 * LandingPage landingPage = new LandingPage(driver); SearchResultsPage
 * searchResultsPage = new SearchResultsPage(driver);
 * 
 * @Given("User is on the landing page") public void
 * user_is_on_the_landing_page() {
 * driver.get("https://demowebshop.tricentis.com/"); }
 * 
 * @When("User searches for {string}") public void user_searches_for(String
 * product) { landingPage.searchProduct(product); landingPage.clickSearchBtn();
 * }
 * 
 * @Then("The relevant products should be displayed") public void
 * the_relevant_products_should_be_displayed() {
 * Assert.assertTrue(searchResultsPage.isProductListDisplayed(),
 * "No relevant products found."); }
 * 
 * @Then("An error message should be displayed") public void
 * an_error_message_should_be_displayed() { String actualMessage =
 * searchResultsPage.getNoResultsMessage();
 * Assert.assertFalse(actualMessage.isEmpty(),
 * "No error message was displayed."); } }
 */