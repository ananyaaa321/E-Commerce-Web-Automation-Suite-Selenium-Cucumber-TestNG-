package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SearchPage {
    WebDriver driver;
    WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "small-searchterms")  
    private WebElement searchBox;

	@FindBy(xpath = "//input[@value='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//strong[contains(text() ,'No products')]") 
    private WebElement noResultsMessage;

    @FindBy(css = ".product-item") 
    private List<WebElement> productList;

 
    //  Sorting 
    @FindBy(id = "products-orderby")  
    private WebElement sortDropdown;

    @FindBy(id = "products-pagesize")  
    private WebElement displayDropdown;
    
    @FindBy(xpath = "//div[@class='details']//h2[@class='product-title']/a") 
    private WebElement firstProductLink;

    //  Filtering Options
    @FindBy(id = "cid")  
    private WebElement categoryDropdown;

    @FindBy(xpath = "//input[@id='As']")  
    private WebElement advancedSearchCheckbox;

    @FindBy(id = "Isc")  
    private WebElement subCategoryCheckbox;
    
    @FindBy(xpath = "//label[@for='Sid']/following::input[1]")
    WebElement searchBtnAdvSearch;
    
    public void enterSearchKeyword(String productName) {
        wait.until(ExpectedConditions.visibilityOf(searchBox)).clear();
        searchBox.sendKeys(productName);
    }

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public boolean isProductListDisplayed() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productList));
        return !productList.isEmpty();
    }

    public String getNoResultsMessage() {
        return wait.until(ExpectedConditions.visibilityOf(noResultsMessage)).getText().trim();
    }
    
    public void selectSortingOption(String sortingType) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)));
        select.selectByVisibleText(sortingType);
    }


    public void selectCategory(String category) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(categoryDropdown)));
        select.selectByVisibleText(category);
    }

    public void enableAdvancedSearch() {
        WebElement advSearch = wait.until(ExpectedConditions.elementToBeClickable(advancedSearchCheckbox));
        if (!advSearch.isSelected()) {
            advSearch.click();
        }
    }

    public void enableSubCategorySearch() {
        if (!subCategoryCheckbox.isSelected()) {
            subCategoryCheckbox.click();
        }
    }
   
    public void selectDisplayOption(String displayCount) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(displayDropdown)));
        select.selectByVisibleText(displayCount);
    }
    public void clickAdvSearchbtn() {
    	   wait.until(ExpectedConditions.elementToBeClickable(searchBtnAdvSearch)).click();
    }

public void clickOnFirstProduct() {
    firstProductLink.click();
}
}

