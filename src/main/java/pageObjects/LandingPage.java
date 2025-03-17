package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {
	
	WebDriver driver; 
	WebDriverWait wait;
	
	@FindBy(linkText = "Register")
	WebElement registerElement;
	
	@FindBy(linkText = "Log in")
	WebElement loginElement;
	
	@FindBy(id = "small-searchterms")
	WebElement searchbarElement;
	
	@FindBy(xpath = "//input[@value='Search']")
	WebElement searchButton;
	
	 @FindBy(linkText = "Books")
	 WebElement booksCategory;

	  @FindBy(linkText = "Jewelry")
	  WebElement jewelryCategory;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        PageFactory.initElements(driver, this);
	}
	public void clickLogInButton() {
		wait.until(ExpectedConditions.elementToBeClickable(loginElement)).click();
	}
	public void clickRegisterButton() {
		wait.until(ExpectedConditions.elementToBeClickable(registerElement)).click();

	}
	public void searchProduct(String product) {
		 wait.until(ExpectedConditions.visibilityOf(searchbarElement)).clear();  
		    wait.until(ExpectedConditions.visibilityOf(searchbarElement)).sendKeys(product);
		
	}
	public void clickSearchBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

	}
	/*
	 * public void selectCategory(String category) { if
	 * (category.equalsIgnoreCase("Books")) {
	 * wait.until(ExpectedConditions.elementToBeClickable(booksCategory)).click(); }
	 * else if (category.equalsIgnoreCase("Jewelry")) {
	 * wait.until(ExpectedConditions.elementToBeClickable(jewelryCategory)).click();
	 * } else { throw new IllegalArgumentException("Invalid category: " + category);
	 * } }
	 */
}

