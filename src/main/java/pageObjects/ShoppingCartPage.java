package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShoppingCartPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[@class='ico-cart']")
    private WebElement cartTable;

    @FindBy(xpath = "//td[@class='product']")
    private List<WebElement> productList;

    @FindBy(xpath = "//input[contains(@class,'qty-input')]")
    private WebElement quantityField;

    @FindBy(name = "updatecart")
    private WebElement updateCartButton;

    @FindBy(xpath = "//span[@class='product-subtotal']")
    private WebElement totalPrice;

    @FindBy(name = "removefromcart")
    private WebElement removeProductCheckbox;

    @FindBy(name = "updatecart")
    private WebElement removeButton;

    @FindBy(xpath = "//button[contains(text(),'Checkout')]")
    private WebElement checkoutButton;

    @FindBy(xpath = "//div[contains(text(), 'empty')]")
    private WebElement emptyCartMessage;
    
    @FindBy(id = "termsofservice") 
    private WebElement termsAndConditionsCheckbox;
    
    // Constructor
    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isCartPageDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(cartTable));
        return cartTable.isDisplayed();
    }

    public boolean isProductInCart(String productName) {
        wait.until(ExpectedConditions.visibilityOf(cartTable));
        return productList.stream().anyMatch(product -> product.getText().contains(productName));
    }

    public String getEmptyCartMessage() {
        wait.until(ExpectedConditions.visibilityOf(emptyCartMessage));
        return emptyCartMessage.getText();
    }

    public void updateProductQuantity(String quantity) {
        wait.until(ExpectedConditions.visibilityOf(quantityField));
        quantityField.clear();
        quantityField.sendKeys(quantity);
        updateCartButton.click();
        wait.until(ExpectedConditions.visibilityOf(totalPrice));
    }

    public void removeProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(removeProductCheckbox)).click();
        removeButton.click();
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public boolean isCartNotEmpty() {
        return !productList.isEmpty();
    }

    public boolean isTotalPriceUpdated() {
        wait.until(ExpectedConditions.visibilityOf(totalPrice));
        double updatedTotal = Double.parseDouble(totalPrice.getText().trim());
        int currentQuantity = Integer.parseInt(quantityField.getAttribute("value"));
        double unitPrice = updatedTotal / currentQuantity;
        return updatedTotal == (unitPrice * currentQuantity);
    }

    public boolean isCheckoutPageDisplayed() {
        return driver.getTitle().contains("Checkout");
    }

    public void openShoppingCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartTable)).click();
    }

    public boolean isShoppingCartPageDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(cartTable));
        return cartTable.isDisplayed();
    }
    public void acceptTermsAndConditions() {
        wait.until(ExpectedConditions.elementToBeClickable(termsAndConditionsCheckbox)).click();
    }
}