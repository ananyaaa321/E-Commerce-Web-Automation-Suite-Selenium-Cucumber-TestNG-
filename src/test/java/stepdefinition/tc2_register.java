package stepdefinition;

import io.cucumber.java.en.*;
import pageObjects.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.time.Duration;

public class tc2_register {
    WebDriver driver= Hooks.getDriver();
    LandingPage landingPage= new LandingPage(driver);
    RegistrationPage registrationPage=new RegistrationPage(driver);
    WebDriverWait wait;

    @Given("User is on the registration page")
    public void user_is_on_registration_page() {
    	driver.get("https://demowebshop.tricentis.com/");
    	landingPage.clickRegisterButton();
    }

    @When("User selects {string} gender")
    public void user_selects_gender(String gender) {
        registrationPage.selectGender(gender);
    }

    @When("User enters {string}, {string}, {string}, {string}, and {string}")  
    public void user_enters_details(String firstName, String lastName, String email, String password, String confirmPassword) {
        registrationPage.enterFirstName(firstName);
        registrationPage.enterLastName(lastName);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(confirmPassword);
    }

    @When("User clicks on the Register button")
    public void user_clicks_on_register_button() {
        registrationPage.clickRegister();
    }

    @Then("Registration should be {string}")
    public void registration_should_be(String expectedResult) {
        if (expectedResult.equalsIgnoreCase("Success")) {
            String message = registrationPage.getRegistrationSuccessMessage();
            Assert.assertTrue(message.contains("Your registration completed"), "Registration was not successful");
        } else {
            Assert.assertTrue(registrationPage.areErrorMessagesDisplayed(), "Error messages were not displayed");
        }
    }

    @Then("User clicks on the Continue button")
    public void user_clicks_on_continue_button() {
        registrationPage.clickContinue();
    }
}
