package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class RegistrationPage {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait of 10 seconds
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "gender-male")
     WebElement genderMale;

    @FindBy(id = "gender-female")
     WebElement genderFemale;

    @FindBy(id = "FirstName")
     WebElement firstName;

    @FindBy(id = "LastName")
     WebElement lastName;

    @FindBy(id = "Email")
     WebElement email;

    @FindBy(id = "Password")
     WebElement password;

    @FindBy(id = "ConfirmPassword")
     WebElement confirmPassword;

    @FindBy(id = "register-button")
    WebElement registerButton;

    @FindBy(className = "result")
     WebElement registrationSuccessMessage;
    
    @FindBy(xpath = "//a[text()='Continue']")
    private WebElement continueButton; 
    
    @FindBy(css = "span.field-validation-error")
    List<WebElement> errorMessages;
    @FindBy(xpath = "//div[@class='validation-summary-errors']")
    WebElement alreadyExists;

   
    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            wait.until(ExpectedConditions.elementToBeClickable(genderMale)).click();
        } else if (gender.equalsIgnoreCase("female")) {
            wait.until(ExpectedConditions.elementToBeClickable(genderFemale)).click();
        }
    }

    public void enterFirstName(String fName) {
        wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys(fName);
    }

    public void enterLastName(String lName) {
        wait.until(ExpectedConditions.visibilityOf(lastName)).sendKeys(lName);
    }

    public void enterEmail(String emailAddress) {
        wait.until(ExpectedConditions.visibilityOf(email)).sendKeys(emailAddress);
    }

    public void enterPassword(String pwd) {
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pwd);
    }

    public void enterConfirmPassword(String confirmPwd) {
        wait.until(ExpectedConditions.visibilityOf(confirmPassword)).sendKeys(confirmPwd);
    }

    public void clickRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    public String getRegistrationSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOf(registrationSuccessMessage)).getText();
    }
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    
    public boolean areErrorMessagesDisplayed() {
    	 return wait.until(ExpectedConditions.or(
    		        ExpectedConditions.visibilityOfAllElements(errorMessages),
    		        ExpectedConditions.visibilityOf(alreadyExists)
    		    ));
    }
}
