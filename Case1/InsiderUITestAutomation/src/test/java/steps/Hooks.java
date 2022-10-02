package steps;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import utils.Driver;

import java.io.File;
import java.time.Duration;

public class Hooks {
    public static WebDriver driver;

    public void setDriver() {
        Driver.get().get("https://useinsider.com/");
        Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Driver.get().manage().window().maximize();
    }

    public void closeDriver() {
        Driver.closeDriver();
    }

    public void captureScreenshot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                // Create refernce of TakesScreenshot
                TakesScreenshot ts = (TakesScreenshot) driver;
                // Call method to capture screenshot
                File source = ts.getScreenshotAs(OutputType.FILE);
                // Copy method  specific location here it will save all screenshot in our project home directory and
                // result.getName() will return name of test case so that screenshot name will be same
                FileHandler.copy(source, new File("./Screenshots/" + result.getName() + ".png"));
                System.out.println("Screenshot taken");
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

}
