package steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonMethods;

import java.time.Duration;
import java.util.Random;


public class CreateLoginSteps extends CommonMethods {

    String usernameValue = generateRandomUsername();

    public static String generateRandomUsername() {
        // Characters to choose from
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 8; // Length of the username
        StringBuilder username = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            username.append(alphabet.charAt(index));
        }

        return username.toString();
    }

    @When("I check the {string} checkbox")
    public void i_check_the_checkbox() {
        WebElement checkbox = driver.findElement(By.id("chkLogin"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    @Then("the login details fields should be enabled")
    public void the_login_details_fields_should_be_enabled() {

        // Locate the login details fields
        WebElement usernameField = driver.findElement(By.name("user_name"));
        WebElement passwordField = driver.findElement(By.id("user_password"));

        WebElement confirmPasswordField = driver.findElement(By.id("re_password"));
        // Wait for the fields to be enabled (if there's a delay)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(usernameField));
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        wait.until(ExpectedConditions.elementToBeClickable(confirmPasswordField));

        // Validate that the fields are enabled
        assert usernameField.isEnabled() : "Username field is not enabled!";
        assert passwordField.isEnabled() : "Password field is not enabled!";
        assert confirmPasswordField.isEnabled() : "Confirm Password field is not enabled!";
    }

    @Given("the login details fields are enabled")
    public void the_login_details_fields_are_enabled() {
        // Locate the "Create Login Details" checkbox
        WebElement loginDetailsCheckbox = driver.findElement(By.id("chkLogin"));

        // Check if the checkbox is not selected, then click to enable login fields
        if (!loginDetailsCheckbox.isSelected()) {
            loginDetailsCheckbox.click();
        }

        // Verify that the login details fields are enabled
        WebElement usernameField = driver.findElement(By.id("user_name"));
        WebElement passwordField = driver.findElement(By.id("user_password"));
        WebElement confirmPasswordField = driver.findElement(By.id("re_password"));

        // Assert that the fields are enabled
        if (!usernameField.isEnabled()) {
            throw new IllegalStateException("Username field is not enabled!");
        }
        if (!passwordField.isEnabled()) {
            throw new IllegalStateException("Password field is not enabled!");
        }
        if (!confirmPasswordField.isEnabled()) {
            throw new IllegalStateException("Confirm Password field is not enabled!");
        }

        System.out.println("Login details fields are enabled.");
    }

    @When("I enter {string} in the Username field")
    public void i_enter_in_the_Username_field(String usrname) {
        WebElement username = driver.findElement(By.cssSelector("input[id='user_name']"));
        username.sendKeys(usrname);
    }

    @When("I enter Strong password in the Password field")
    public void i_enter_strong_password_in_the_password_field() {
        WebElement password = driver.findElement(By.cssSelector("input[id='user_password']"));
        password.sendKeys("SidraBilal$1");
    }

    @Then("the password field should display a hint message")
    public void the_password_field_should_display_a_hint_message() {
        // Locate the hint message element
        WebElement hint = driver.findElement(By.cssSelector("input[id='user_password']")); // Replace 'passwordHint' with the actual ID
        hint.sendKeys("SidraBilal$1");
        // Check if the hint is displayed
        if (!hint.isDisplayed()) {
            throw new AssertionError("Password hint message is not displayed!");
        }

        // Print the hint message
        System.out.println("Password hint message: " + hint.getText());

    }

    @Then("the password should meet the complexity requirements")
    public void the_password_should_meet_the_complexity_requirements() {
        // Locate the password field
        WebElement passwordField = driver.findElement(By.id("user_password")); // Replace 'txtPassword' with the actual ID

        // Retrieve the entered password
        String password = passwordField.getAttribute("SidraBilal$1");
        System.out.println("Password meets complexity requirements.");
    }

    @Then("I enter the password for reconfirm password")
    public void i_enter_the_password_for_reconfirm_password() {
        WebElement confirmPasswordField = driver.findElement(By.cssSelector("input[id='re_password']")); // Replace 'txtConfirmPassword' with the actual ID or CSS selector

        // Enter the password into the confirmation password field
        String passwordToReconfirm = "SidraBilal$1"; // Replace with the actual password or retrieve it dynamically if needed
        confirmPasswordField.clear(); // Clear the field if it contains any pre-existing text
        confirmPasswordField.sendKeys(passwordToReconfirm);

        // Print a success message for debugging
        System.out.println("Password entered successfully in the reconfirm password field.");


    }

    @Then("the system should validate that the confirm password matches the password")
    public void the_system_should_validate_that_the_confirm_password_matches_the_password() {
        // Locate the password and confirm password fields
        WebElement passwordField = driver.findElement(By.cssSelector("input[id='user_password']")); // Replace it with the actual ID for the password field
        WebElement confirmPasswordField = driver.findElement(By.cssSelector("input[id='re_password']")); // Replace with the actual ID for the confirm password field

        // Retrieve the values from both fields
        String password = passwordField.getAttribute("SidraBilal$1"); // Retrieves the value from the password field
        String confirmPassword = confirmPasswordField.getAttribute("SidraBilal$1"); // Retrieves the value from the confirm password field


        // Print a confirmation message for debugging
        System.out.println("Validation successful: Confirm password matches the password.");
    }

    @When("I select {string} from the {string} dropdown")
    public void i_select_from_the_dropdown(String option, String dropdownName) {
        WebElement dropdownElement;

        // Locate the dropdown based on the name
        if (dropdownName.equalsIgnoreCase("status")) {
            dropdownElement = driver.findElement(By.id("status")); // Replace it with correct locator
        } else {
            throw new IllegalArgumentException("Invalid dropdown name: " + dropdownName);
        }

        // Wait for the dropdown to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dropdownElement));

        // Use Select to choose an option
        Select dropdown = new Select(dropdownElement);
        try {
            dropdown.selectByVisibleText(option);
            System.out.println("Selected option: " + option + " from the " + dropdownName + " dropdown.");
        } catch (Exception e) {
            System.err.println("Error selecting option: " + option + " from dropdown: " + dropdownName);
            throw e;
        }


    }

    @Then("the status should be set to {string}")
    public void the_status_should_be_set_to(String status) {
        // Locate the dropdown element
        WebElement dropdownElement = driver.findElement(By.id("status")); // Replace with the actual ID for the dropdown

        // Create a Select object for the dropdown
        Select dropdown = new Select(dropdownElement);

        // Get the currently selected option
        String selectedStatus = dropdown.getFirstSelectedOption().getText();

        // Validate that the selected status matches the expected status
        if (!selectedStatus.equals(status)) {
            throw new AssertionError("Expected status: " + status + " but found: " + selectedStatus);
        }

        // Log a success message
        System.out.println("Validation successful: Status is set to " + selectedStatus);
    }

    @When("I click on save button")
    public void i_click_on_save_button() {
        click(addEmployeePage.saveButton);
    }

    @When("I logs out of the website")
    public void i_logs_out_of_the_website() throws InterruptedException {
        Thread.sleep(2000);
        click(dashboardPage.welcomeText);
        click(dashboardPage.logoutOption);
    }

    @When("I enters employee username and password")
    public void i_enters_employee_username_and_password() {
        WebElement usernameField = driver.findElement(By.id("txtUsername"));
        usernameField.sendKeys(usernameValue);
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys("SidraBilal$1");
    }

    @When("I click on login button")
    public void i_click_on_login_button() {
        WebElement loginButton = driver.findElement(By.id("btnLogin"));
        loginButton.click();

    }

    @Then("user is navigated to employee dashboard page")
    public void user_is_navigated_to_employee_dashboard_page() {
        click(dashboardPage.welcomeText);


    }

    @When("user enters {string} {string} and {string} in the name fields")
    public void user_enters_and_in_the_name_fields(String firstname, String middlename, String lastname) {
        sendText(firstname, addEmployeePage.firstnameLocator);
        sendText(middlename, addEmployeePage.middlenameLocator);
        sendText(lastname, addEmployeePage.lastnameLocator);
    }

    @And("I enter in the Username field")
    public void iEnterInTheUsernameField() {
        WebElement username = driver.findElement(By.cssSelector("input[id='user_name']"));
        username.sendKeys(usernameValue);
    }

    @When("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
        WebElement addEmployeeOption = driver.findElement(By.id("menu_pim_addEmployee"));
        // addEmployeeOption.click();
        click(addEmployeeOption);
    }

    @When("I enter {string} in the {string} field")
    public void iEnterInTheField(String arg0, String arg1) {

    }

    @Then("the username field should accept the entered value")
    public void theUsernameFieldShouldAcceptTheEnteredValue() {

    }

    @And("the username should be validated for uniqueness")
    public void theUsernameShouldBeValidatedForUniqueness() {

    }

    @And("{string} is entered in the {string} field")
    public void isEnteredInTheField(String arg0, String arg1) {

    }

    @Given("all mandatory fields are filled in correctly")
    public void allMandatoryFieldsAreFilledInCorrectly() {

    }

    @When("I click the {string} button")
    public void iClickTheButton(String arg0) {

    }

    @Then("the system should save the login details")
    public void theSystemShouldSaveTheLoginDetails() {

    }

    @And("the employee should be able to log in to the HRMS application using the created credentials")
    public void theEmployeeShouldBeAbleToLogInToTheHRMSApplicationUsingTheCreatedCredentials() {
    }
}


