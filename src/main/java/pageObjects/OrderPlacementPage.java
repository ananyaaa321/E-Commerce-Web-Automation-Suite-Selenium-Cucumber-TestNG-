package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class OrderPlacementPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "billing-address-select") // Billing address dropdown
    private WebElement billingAddressDropdown;
    
    @FindBy(id = "BillingNewAddress_FirstName")
    private WebElement firstNameField;

    @FindBy(id = "BillingNewAddress_LastName")
    private WebElement lastNameField;

    @FindBy(id = "BillingNewAddress_Email")
    private WebElement emailField;

    @FindBy(id = "BillingNewAddress_CountryId")
    private WebElement countryDropdown;

    @FindBy(id = "BillingNewAddress_City")
    private WebElement cityField;

    @FindBy(id = "BillingNewAddress_Address1")
    private WebElement addressField;

    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    private WebElement zipCodeField;

    @FindBy(id = "BillingNewAddress_PhoneNumber")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//input[@class='button-1 new-address-next-step-button']")
    private WebElement saveBillingButton;
    
    @FindBy(xpath = "//input[@onclick='Shipping.save()']")
    WebElement saveShipping;

    @FindBy(id = "shippingoption_0") // Ground Shipping
    private WebElement groundShippingOption;

    @FindBy(id = "paymentmethod_0") // Cash on Delivery
    private WebElement cashOnDeliveryOption;
    
    @FindBy(id = "paymentmethod_1") // Cheque/Money Order
    WebElement chequeMoneyOrderOption;

    @FindBy(id = "paymentmethod_1") // Card Payment
    private WebElement cardPaymentOption;

    @FindBy(xpath = "//input[@class='button-1 shipping-method-next-step-button']")
    private WebElement saveShippingMethButton;
    
    @FindBy(xpath = "//input[@class='button-1 payment-method-next-step-button']")
    private WebElement savePaymentMethButton;
    
    @FindBy(xpath = "//input[@class='button-1 payment-info-next-step-button']")
    private WebElement savePaymentInfoButton;

    @FindBy(xpath  = "//input[@class='button-1 confirm-order-next-step-button']")
    private WebElement confirmOrderButton;

    @FindBy(xpath = "//strong[text()='Your order has been successfully processed!']") // Success message locator
    private WebElement orderSuccessMessage;

    @FindBy(xpath = "//Span[@class='field-validation-error']") // Error message for invalid details
    private WebElement errorMessage;

    // Constructor
    public OrderPlacementPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

	
    public boolean enterBillingDetails(String country, String city, String address, String zipCode, String phoneNumber) {
        wait.until(ExpectedConditions.visibilityOf(countryDropdown));
        new Select(countryDropdown).selectByVisibleText(country);
        
        cityField.sendKeys(city);
        addressField.sendKeys(address);
        zipCodeField.sendKeys(zipCode);
        phoneNumberField.sendKeys(phoneNumber);
        
        saveBillingButton.click();

        // Check for error message after submitting billing details
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return false; // Error found, invalid details
        } catch (Exception e) {
            return true; // No error, details are valid
        }
        
    }

  public void saveShippingAddress() {
    wait.until(ExpectedConditions.elementToBeClickable(saveShipping)).click();
  }

    public void selectShippingMethod() {
        wait.until(ExpectedConditions.elementToBeClickable(groundShippingOption)).click();
        saveShippingMethButton.click();
    }

    public void selectPaymentMethod(String method) {
        String trimmedMethod = method.trim(); 

        if (method.equalsIgnoreCase("Cash on Delivery")) {
            wait.until(ExpectedConditions.elementToBeClickable(cashOnDeliveryOption)).click();
        } else if (trimmedMethod.equalsIgnoreCase("Check / Money Order (5.00)")) {
            wait.until(ExpectedConditions.elementToBeClickable(chequeMoneyOrderOption)).click();
        }
   
       savePaymentMethButton.click();
       wait.until(ExpectedConditions.elementToBeClickable(savePaymentInfoButton)).click();

    }

    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton)).click();
    }

    public String getOrderConfirmationMessage() {
        wait.until(ExpectedConditions.visibilityOf(orderSuccessMessage));
        return orderSuccessMessage.getText();
    }
    public void selectNewBillingAddress() {
        wait.until(ExpectedConditions.visibilityOf(billingAddressDropdown));
        Select select = new Select(billingAddressDropdown);
        select.selectByVisibleText("New Address");
    }
   
    }



