package stepdefinition;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Utility.Log4jLogger;

import Utility.*;


public class Hooks {
    private static WebDriver driver = null; 

    @Before
    public void setUp(Scenario scenario) {
        if (driver == null) { 
            driver = new ChromeDriver();
            driver.manage().window().maximize(); 
            Log4jLogger.info("Launching browser");
            ExtentReportManager.startTest(scenario.getName());
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                Log4jLogger.error("Test Failed: " + scenario.getName());
                String screenshotPath = Utility.CaptureScreenshot.capture(driver, scenario.getName());
                ExtentReportManager.logStep("FAIL", "Test failed: " + scenario.getName(), scenario.getName(), driver);
            } else {
                ExtentReportManager.logStep("PASS", "Test passed: " + scenario.getName(), scenario.getName(), driver);
            }
        } catch (Exception e) {
            Log4jLogger.error("Error during teardown: " + e.getMessage());
        } finally {
        	 ExtentReportManager.endTest();  
             ExtentReportManager.flushReport();
            if (driver != null) {
                driver.quit();
                driver = null;
                Log4jLogger.info("Browser closed");
            }
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
