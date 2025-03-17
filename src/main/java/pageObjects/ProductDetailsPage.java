package pageObjects;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class ProductDetailsPage {
    WebDriver driver;
    WebDriverWait wait;

   
    @FindBy(xpath = "//h1[@itemprop='name']")
    private WebElement productTitle;

    @FindBy(xpath = "//span[@itemprop='price']")
    private WebElement productPrice;
    
    @FindBy(xpath = "//div[@class='short-description']")
    private WebElement productDescription;
    
    @FindBy(xpath = "//div[@class='gallery']")
    private WebElement productImages;
    
    @FindBy(xpath = "//div[@class='stock']/span[@class='value']")
    private WebElement productAvailability;
	

    @FindBy(xpath = "//input[@class='button-1 add-to-cart-button']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//input[@class='button-2 add-to-wishlist-button']")
    private WebElement addToWishlistButton;

    @FindBy(className = "bar-notification")
    private WebElement notificationMessage;

    @FindBy(xpath = "//a[contains(@href,'/cart')]")
    private WebElement shoppingCartLink;

    @FindBy(xpath = "//a[contains(@href,'/wishlist')]")
    private WebElement wishlistLink;

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public String getProductTitle() {
        wait.until(ExpectedConditions.visibilityOf(productTitle));
        return productTitle.getText();
    }

    public boolean isProductDescriptionVisible() {
        wait.until(ExpectedConditions.visibilityOf(productDescription));
        return productDescription.isDisplayed();
    }

    public boolean isProductPriceVisible() {
        wait.until(ExpectedConditions.visibilityOf(productPrice));
        return productPrice.isDisplayed();
    }

    public boolean isProductImagesVisible() {
        wait.until(ExpectedConditions.visibilityOf(productImages));
        return productImages.isDisplayed();
    }

    public boolean isNotificationMessageDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(notificationMessage));
        return notificationMessage.isDisplayed();
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        wait.until(ExpectedConditions.visibilityOf(notificationMessage));
    }

    public boolean addToWishlist() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addToWishlistButton)).click();
            wait.until(ExpectedConditions.visibilityOf(notificationMessage));
            return notificationMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false; // wishlist button is missing
        }
    }

    public void goToShoppingCart() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink)).click();
    }

    public void goToWishlist() {
        wait.until(ExpectedConditions.elementToBeClickable(wishlistLink)).click();
    }

    public boolean isProductAvailable() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productAvailability));
            return productAvailability.getText().trim().equalsIgnoreCase("In stock");
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}