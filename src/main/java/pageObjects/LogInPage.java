package pageObjects;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInPage {

	WebDriver driver; 
	WebDriverWait wait;
	 @FindBy(id = "Email")   WebElement emailField;
	 @FindBy(id = "Password")   WebElement passwordField;
     @FindBy(xpath = "//input[@class='button-1 login-button']") WebElement loginBtn;
	 @FindBy(className = "message-error")   WebElement errorMessage;
	  @FindBy(linkText = "Log out")  WebElement logoutLink;
	    
	
	public LogInPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        PageFactory.initElements(driver, this);
	}
	public void enterUsername(String username) {
	    wait.until(ExpectedConditions.visibilityOf(emailField)).clear(); 
	    wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(username);
	}

	public void enterPassword(String password) {
	    wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();  
	    wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
	}


	    public void clickLogin() {
	        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
	    }
	    public boolean isErrorMessageDisplayed() {
	        try {
	            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
	        } catch (TimeoutException e) {
	            return false; 
	        }
	    }
	    

	    public String getLoginErrorMessage() {
	        try {
	            return errorMessage.getText();
	        } catch (Exception e) {
	            return "";
	        }
	    }

	    public boolean isLogoutLinkDisplayed() {
	        try {
	            return logoutLink.isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public void clickLogout() {
	        if (isLogoutLinkDisplayed()) {
	            logoutLink.click();
	        }
	    }
	}


