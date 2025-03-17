package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//div[@class='block block-category-navigation']")  
    private WebElement categoriesSection;

    @FindBy(id = "small-searchterms")  
    private WebElement searchBar;

   
    @FindBy(xpath = "//div[@class='header-links']")  
    private WebElement headerSection;

    @FindBy(xpath = "//div[@class='footer']")  
    private WebElement footerSection;

   

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

   
    public boolean isCategoriesVisible() {
        wait.until(ExpectedConditions.visibilityOf(categoriesSection));
        return categoriesSection.isDisplayed();
    }

    public boolean isSearchBarVisible() {
        wait.until(ExpectedConditions.visibilityOf(searchBar));
        return searchBar.isDisplayed();
    }

    public boolean isHeaderVisible() {
        wait.until(ExpectedConditions.visibilityOf(headerSection));
        return headerSection.isDisplayed();
    }

    public boolean isFooterVisible() {
        wait.until(ExpectedConditions.visibilityOf(footerSection));
        return footerSection.isDisplayed();
    }

   
}
