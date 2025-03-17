package Utility;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;

public class ExtentReportManager {
    private static ExtentReports extent;
    public static ExtentTest test;

    // Start Report
    public static void startReport() {
        if (extent == null) {  // Prevent multiple instances
            extent = new ExtentReports("C:\\Users\\Administrator\\seleniumBDD\\capstoneProject\\test-output\\ExtentReport.html", true);
            extent.addSystemInfo("Host Name", "Localhost")
                  .addSystemInfo("Environment", "QA")
                  .addSystemInfo("User Name", "Test Automation");
        }
    }

    // Start Test Case
    public static void startTest(String testCaseName) {
        test = extent.startTest(testCaseName);
    }

    // Log Step with Screenshot for Failures
    public static void logStep(String status, String message, String testCaseName, WebDriver driver) throws InterruptedException {
        if (status.equalsIgnoreCase("PASS")) {
            test.log(LogStatus.PASS, message);
        } else if (status.equalsIgnoreCase("FAIL")) {
			
            test.log(LogStatus.FAIL, message);  // ✅ No screenshot attached

        } else if (status.equalsIgnoreCase("INFO")) {
            test.log(LogStatus.INFO, message);
        } else {
            test.log(LogStatus.UNKNOWN, message);
        }
    }
    public static void endTest() {
        if (test != null) {
            extent.endTest(test);  // ✅ Ensuring test is ended before flush
            test = null;
        }
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static void closeReport() {
        if (extent != null) {
            extent.flush();
            extent.close();  // ✅ Only close once, at the end of all tests
            extent = null;
        }
    }
}
