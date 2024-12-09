package utils;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CommonMethods extends PageInitializer {

    public static WebDriver driver;


    public static void sendText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public static WebElement getVisibleElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void openBrowserAndLaunchApplication() {
        switch (ConfigReader.read("browser")) {
            case "Chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments(" --headless");
                driver = new ChromeDriver(options);
                break;
            case "FireFox":
                driver = new FirefoxDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            case "Safari":
                driver = new SafariDriver();
                break;
            default:
                throw new RuntimeException("Invalid Browser Name");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(ConfigReader.read("url"));
        initializePageObjects();
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void selectFromDropDown(WebElement dropDown, int index) {
        Select sel = new Select(dropDown);
        sel.selectByIndex(index);
    }

    public JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) driver;
    }

    public void jsClick(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click();", element);
    }

    public byte[] takeScreenshot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] picByte = ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile, new File(Constants.SCREENSHOT_FILEPATH + fileName + " " + getTimeStamp("yyyy-MM-dd-HH-mm-ss") + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picByte;
    }

    public String getTimeStamp(String pattern) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public void sendKeysWithVerification(WebElement element, String value) {
        if (value != null && !value.trim().isEmpty()) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
                clickableElement.clear();
                clickableElement.sendKeys(value);
                String actualValue = clickableElement.getAttribute("value");
                Assert.assertEquals("Value not set correctly for element", value, actualValue);
            } catch (Exception e) {
                try {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].value = arguments[1];", element, value);
                    String jsSetValue = (String) js.executeScript("return arguments[0].value", element);
                    Assert.assertEquals("JS value not set correctly", value, jsSetValue);
                } catch (Exception jsError) {
                    throw new RuntimeException("Failed to send keys to element: " + jsError.getMessage());
                }
            }
        }
    }

    public void waitForElementToBeVisible(WebElement element) {
        getwait().until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeClickAble(WebElement element) {
        getwait().until(ExpectedConditions.elementToBeClickable(element));

    }

    public static String getAttribute(WebElement element, String value) {
        return element.getAttribute(value);
    }


    public void sendText(String text, WebElement element) {
        waitForElementToBeClickAble(element);
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }


    public void click(WebElement element) {
        waitForElementToBeClickAble(element);
        element.click();
    }

    public static void selectDropdownOption(WebElement dropdownElement, String visibleText) {
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(visibleText);
    }

    public static WebDriverWait getwait() {
        return new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT));


    }
}
