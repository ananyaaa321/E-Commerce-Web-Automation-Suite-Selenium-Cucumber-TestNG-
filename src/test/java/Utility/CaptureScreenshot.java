package Utility;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class CaptureScreenshot {
    public static String capture(WebDriver driver, String testName) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);

        testName = testName.replaceAll("[^a-zA-Z0-9_-]", "_"); 

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
       // String screenshotPath = "C:\\Users\\Administrator\\eclipse-workspace\\mockproject2\\screenshots\\" + testName + ".png";
        String screenshotPath = "C:\\Users\\Administrator\\seleniumBDD\\capstoneProject\\screenshots\\" 
                + testName + "_" + timestamp + ".png"; // Unique filename
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotPath);
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved: " + destFile.getAbsolutePath());
            return destFile.getAbsolutePath();
        } catch (IOException e) {
            System.out.println("Error capturing screenshot: " + e.getMessage());
            return null;
        }
    }
}
