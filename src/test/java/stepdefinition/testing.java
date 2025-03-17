/*
 * package stepdefinition;
 * 
 * import org.openqa.selenium.By; import org.openqa.selenium.WebDriver; import
 * org.openqa.selenium.WebElement; import
 * org.openqa.selenium.chrome.ChromeDriver; import org.testng.Assert;
 * 
 * import io.cucumber.java.en.Given; import io.cucumber.java.en.Then; import
 * io.cucumber.java.en.When;
 * 
 * public class testing { WebDriver driver = new ChromeDriver();
 * 
 * 
 * @Given("User is in login page") public void user_is_in_login_page() {
 * driver.get("https://demowebshop.tricentis.com/login"); }
 * 
 * @When("User enters credentials") public void user_enters_credentials() {
 * driver.findElement(By.id("Email")).sendKeys("testuser@example.com");
 * driver.findElement(By.id("Password")).sendKeys("password123");
 * driver.findElement(By.xpath("//input[@value='Log in']")).click(); }
 * 
 * @Then("User should see welcome page") public void
 * user_should_see_welcome_page() { // WebElement welcomeMessage =
 * driver.findElement(By.xpath("//a[@class='account']"));
 * //Assert.assertTrue(welcomeMessage.isDisplayed(),
 * "Welcome message is not displayed"); System.out.println("Hello");
 * driver.quit(); } }
 */