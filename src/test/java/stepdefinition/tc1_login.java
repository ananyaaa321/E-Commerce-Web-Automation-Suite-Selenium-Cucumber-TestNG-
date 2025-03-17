package stepdefinition;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Utility.Log4jLogger;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LandingPage;
import pageObjects.LogInPage;


public class tc1_login {
    WebDriver driver = Hooks.getDriver();
    LandingPage landingPage = new LandingPage(driver);
    LogInPage logInPage = new LogInPage(driver);
    
    String expectedResult;
    boolean allFieldsPresent;  // Flag to track missing fields

    String filePath = "C:\\Users\\Administrator\\seleniumBDD\\capstoneProject\\LoginData.xlsx";

    @Given("User is on the login page")
    public void userIsOnTheLoginPage() {
        driver.get("https://demowebshop.tricentis.com/");
        landingPage.clickLogInButton();
        Log4jLogger.info("User is on login Page");

    }

    @When("user enters the email and password from the excel file")
    public void user_enters_the_email_and_password_from_the_excel_file() throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet("Sheet1");

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                // Read email & password
                String email = (row.getCell(0) != null) ? row.getCell(0).getStringCellValue().trim() : "";
               System.out.println("Email is "+ email);
               
               String password = (row.getCell(1) != null) ? row.getCell(1).getStringCellValue().trim() : "";
               System.out.println("Password is "+ password);
               expectedResult = (row.getCell(2) != null) ? row.getCell(2).getStringCellValue().trim() : "";

                // Check if both fields are present
                allFieldsPresent = !email.isEmpty() && !password.isEmpty();

                if (!allFieldsPresent) {
                    System.out.println("Row " + (row.getRowNum() + 1) + " - Missing email or password. ");
                } else {
                    landingPage.clickLogInButton();

                    logInPage.enterUsername(email);
                    logInPage.enterPassword(password);
                    logInPage.clickLogin();
                }
                validateLogin();
            }
        }
    }

    @Then("user reaches home page or error message is displayed")
    public void validateLogin() {
        if (expectedResult.equalsIgnoreCase("Success")) {
            Assert.assertFalse(logInPage.getLoginErrorMessage().contains("unsuccessful"),
                    "User should be logged in successfully");
   
            logInPage.clickLogout();
        } else {
            //  If any field was missing, it should fail
            Assert.assertTrue(!allFieldsPresent || logInPage.getLoginErrorMessage().contains("Login was unsuccessful"),
                    "Login should fail but it didn't.");
            

        }
    }
}
