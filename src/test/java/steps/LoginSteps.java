package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;

import java.io.IOException;

public class LoginSteps extends CommonMethods {

    @Given("user is able to access HRMS application")
    public void user_is_able_to_access_hrms_application() throws IOException {
        openBrowserAndLaunchApplication();
    }

    @When("user enters valid Admin Username and Admin Password")
    public void user_enters_valid_admin_username_and_admin_password() throws IOException {
        sendText(ConfigReader.read(Constants.USERNAME_FIELD), loginPage.usernameField);
        sendText(ConfigReader.read(Constants.PASSWORD_FIELD), loginPage.passwordField);

    }

    @When("user clicks on Login button")
    public void user_clicks_on_login_button() {
        click(loginPage.loginButton);

    }

    @Then("user is navigated to dashboard page")
    public void user_is_navigated_to_dashboard_page() {
        Assert.assertTrue(dashboardPage.welcomeText.isDisplayed());

    }

    @Then("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        click(dashboardPage.pimOption);
    }

    //for AddDependentsSteps

    @Then("user clicks on Employee List")
    public void user_clicks_on_employee_list() {
        click(loginPage.employeeListOption);
    }

}
