package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SearchResultsPage {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".product-item") 
     List<WebElement> productList; 

    @FindBy(xpath = "//strong[contains(text() ,'No products')]") 
     WebElement noResultsMessage; // Error message when no product is found

    // Method to check if products are displayed
    public boolean isProductListDisplayed() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productList));
        return !productList.isEmpty();
    }

    // Method to fetch the "No products found" message
    public String getNoResultsMessage() {
        return wait.until(ExpectedConditions.visibilityOf(noResultsMessage)).getText();
    }
}
