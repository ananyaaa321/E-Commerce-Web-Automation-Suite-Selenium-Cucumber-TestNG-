package stepdefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.cucumber.java.en.*;
import pageObjects.HomePage;
import pageObjects.LogInPage;

public class tc4_HomePageVerification {
    WebDriver driver = Hooks.getDriver();
    HomePage homePage = new HomePage(driver);
    LogInPage loginPage = new LogInPage(driver);

    @When("User logs in with {string} and {string} and lands on the home page")
    public void user_logs_in_with_credentials_and_lands_on_the_home_page(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @Then("The home page elements should be visible")
    public void the_home_page_elements_should_be_visible() {
    	if (homePage.isCategoriesVisible()) {
            System.out.println("Categories section is displayed");
        }
        Assert.assertTrue(homePage.isCategoriesVisible(), "Categories section is not visible!");

        if (homePage.isSearchBarVisible()) {
            System.out.println("Search bar is displayed");
        }
        Assert.assertTrue(homePage.isSearchBarVisible(), "Search bar is not visible!");

        if (homePage.isHeaderVisible()) {
            System.out.println("Header section is displayed");
        }
        Assert.assertTrue(homePage.isHeaderVisible(), "Header section is not visible!");

        if (homePage.isFooterVisible()) {
            System.out.println("Footer section is displayed");
        }
        Assert.assertTrue(homePage.isFooterVisible(), "Footer section is not visible!");
    

        
    }
}
