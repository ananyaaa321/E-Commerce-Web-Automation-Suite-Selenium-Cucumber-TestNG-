import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import Utility.ExtentReportManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions( features = "src/test/resources/features",
    glue = "stepdefinition",
    tags = "@test",
    plugin = {"pretty",
    		"html:target/cucumber-reports.html",
    		"io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm" 
    		},
    monochrome = true)
public class TestRunner extends  AbstractTestNGCucumberTests {
	 @BeforeClass
	    public void setup() {
	        ExtentReportManager.startReport();
	      
	    }

	    @AfterClass
	    public void tearDown() {
	        ExtentReportManager.flushReport();
	    }
}
