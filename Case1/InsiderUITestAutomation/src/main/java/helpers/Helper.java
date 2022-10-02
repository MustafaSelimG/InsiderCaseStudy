package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Driver;

import java.time.Duration;
import java.util.ArrayList;

public class Helper {
    private static final WebDriver driver = Driver.get();
    private static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    static Actions actions = new Actions(driver);

    public static void assertUrl(String expectedUrl) {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl);
    }

    public static void assertUrlContains(String expectedUrl) {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(expectedUrl));
    }

    public static void assertElementIsDisplayed(WebElement element) {
        Assert.assertTrue(element.isDisplayed());
    }

    public static void assertElementIsEnabled(WebElement element) {
        Assert.assertTrue(element.isEnabled());
    }

    public static void clickElementByLinkText(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

    public static void assertElementText(WebElement element, String expectedText) {
        Assert.assertEquals(element.getText(), expectedText);
    }

    public static void assertElementTextContains(WebElement element, String expectedText) {
        Assert.assertTrue(element.getText().contains(expectedText));
    }

    public static void assertElementTextContains(WebElement element, String expectedText1, String expectedText2) {
        Assert.assertTrue(element.getText().contains(expectedText1) || element.getText().contains(expectedText2));
    }

    public static void click(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public static void switchLastTab() {
        ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTb.get(1));
    }

    public static void switchToMainTab() {
        ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTb.get(0));
    }

    public static void closeTab() {
        driver.close();
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForAjax() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return jQuery.active == 0");
    }

    public static void wait(int second) {

        try {
            Thread.sleep(second * 1000L);
        } catch (Exception e) {
        }
    }

    public static WebElement findByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

}
