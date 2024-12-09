package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonMethods;
import utils.ConfigReader;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;

public class AddingEmployeeSteps extends CommonMethods {
    WebDriver driver = CommonMethods.driver;
    WebDriverWait wait;
    String generatedEmployeeID;
    String firstName;
    String lastName;

    public static String generateRandomEmployeeID() {
        Random random = new Random();
        int randomID = 100000 + random.nextInt(900000);
        return String.valueOf(randomID);
    }

    public boolean doesEmployeeExist(String firstName, String lastName) {
        try {
            driver.findElement(By.xpath("//a[text()='" + firstName + " " + lastName + "']"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @When("user enters admin username and admin password")
    public void user_enters_admin_username_and_admin_password() {
        sendText(ConfigReader.read("userName"), loginPage.usernameField);
        sendText(ConfigReader.read("password"), loginPage.passwordField);
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        click(loginPage.loginButton);
    }

    @When("user enters an employeeID")
    public void user_enters_an_employee_id() {
        sendText(generateRandomEmployeeID(), driver.findElement(By.id("employeeId")));
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        click(addEmployeePage.saveButton);
    }

    @Then("the system should generate a unique employee ID for the new employee")
    public void the_system_should_generate_a_unique_employee_id_for_the_new_employee() {
        WebElement employeeIDField = driver.findElement(By.id("employeeId"));
        Assert.assertNotNull("Employee ID should not be null", employeeIDField);
        Assert.assertFalse("Employee ID should not be empty", Objects.requireNonNull(employeeIDField.getAttribute("value")).isEmpty());
    }

    @When("the employee should be added to the system with name {string} and {string} and {string}")
    public void the_employee_should_be_added_to_the_system_with_name_and_and
            (String fn, String mn, String ln) throws InterruptedException {
        WebElement personalDetails = driver.findElement(By.xpath("//h1[text()='Personal Details']"));
        Assert.assertTrue(personalDetails.isDisplayed());
        WebElement firstName = driver.findElement(By.xpath("//input[@value='" + fn + "']"));
        String firstNameText = firstName.getAttribute("value");
        //this assertion did not want to work with assertEquals, so I used assertTrue
        Assert.assertEquals(fn, firstNameText);
        WebElement middleName = driver.findElement(By.xpath("//input[@value='" + mn + "']"));
        Assert.assertEquals(mn, middleName.getAttribute("value"));
        WebElement lastName = driver.findElement(By.xpath("//input[@value='" + ln + "']"));
        Assert.assertEquals(ln, lastName.getAttribute("value"));
    }

    @Then("the employee should be added to the system with the provided details")
    public void the_employee_should_be_added_to_the_system_with_the_provided_details() {
        WebElement personalDetails = driver.findElement(By.xpath("//h1[text()='Personal Details']"));
        Assert.assertTrue(personalDetails.isDisplayed());
    }

    @Then("the system should display a last name error message {string}")
    public void the_system_should_display_a_last_name_error_message(String errorMessage) {
        Assert.assertEquals(addEmployeePage.error.getText(), errorMessage);
    }

    @Then("the system should display an error message {string}")
    public void the_system_should_display_an_error_message(String errorMessage) {
        Assert.assertEquals(addEmployeePage.lastNameError.getText(), errorMessage);
    }
}
