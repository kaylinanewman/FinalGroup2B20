package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;
import utils.PageInitializer;

import java.io.File;
import java.time.Duration;

public class ProfilePictureUploadSteps extends CommonMethods {
    WebDriver driver = Hooks.driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

    @Given("I am logged into the HRMS application as an ESS user,")
    public void i_am_logged_into_the_hrms_application_as_an_ess_user() {
        PageInitializer.initializePageObjects();
        openBrowserAndLaunchApplication();
        sendText(ConfigReader.read("MuserName"), driver.findElement(By.id(Constants.USERNAME_FIELD)));
        sendText(ConfigReader.read("Mpassword"), driver.findElement(By.id(Constants.PASSWORD_FIELD)));
        driver.findElement(By.id(Constants.LOGIN_BUTTON)).click();
    }

    @When("I navigate to the profile picture upload page,")
    public void i_navigate_to_the_profile_picture_upload_page() {
        WebElement myProfileMenuButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(Constants.MY_INFO_MENU)));
        myProfileMenuButton.click();
    }

    @Then("I click profile picture to change the photo,")
    public void iClickProfilePictureToChangeThePhoto() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement uploadButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.CHANGE_PHOTO_OPTION)));
        uploadButton.click();

    }

    @And("I select a profile picture {string} with a size of {} bytes,")
    public void iSelectAProfilePictureWithASizeOfBytes(String Filename, int sizeInBytes) {
        try {
            String filePath = new File(Constants.BASE_PATH + Filename).getAbsolutePath();
            WebElement choosePhoto = driver.findElement(By.xpath(Constants.CHANGE_PHOTO_OPTION));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement chooseFileInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.CHOOSE_FILE_OPTION)));
            chooseFileInput.sendKeys(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("I click on the {string} button,")
    public void i_click_on_the_button(String Upload) {
        WebElement uploadButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.UPLOAD_BUTTON)));
        uploadButton.click();

    }

    @Then("I should see a success message {string},")
    public void iShouldSeeASuccessMessage(String successMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.SUCCESS_MESSAGE)));

        String messageText = messageElement.getText().trim().replaceAll("\\s+", " ");
        String normalizedExpectedMessage = successMessage.trim().replaceAll("\\s+", " ");
        if (!normalizedExpectedMessage.trim().contains(messageText)) {
            throw new AssertionError("Expected message: " + successMessage + ", but got: " + messageText);
        }
    }

    @Then("the profile picture should be displayed.")
    public void theProfilePictureShouldBeDisplayed() {
        By profilePictureLocator = By.xpath(Constants.PROFILE_PICTURE);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(profilePictureLocator));

        WebElement profilePicture = driver.findElement(profilePictureLocator);
        Assert.assertTrue(profilePicture.isDisplayed());
    }

    @But("if the upload fails, I should see an error message {string}.")
    public void ifTheUploadFailsIShouldSeeAnErrorMessage(String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.ERROR_MESSAGE)));
        String actualErrorMessageText = errorMessageElement.getText().trim().replaceAll("\\s+", " ");
        String normalizedExpectedErrorMessage = errorMessage.trim().replaceAll("\\s+", " ");

        if (!normalizedExpectedErrorMessage.equals(actualErrorMessageText)) {
            throw new AssertionError("Expected error message: " + errorMessage + ", but got: " + actualErrorMessageText);
        }
    }

    @Then("I close the browser")
    public void i_close_the_browser() {
        closeBrowser();
    }
}
