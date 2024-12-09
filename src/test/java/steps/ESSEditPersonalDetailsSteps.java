package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;

public class ESSEditPersonalDetailsSteps extends CommonMethods {
    @When("user enters ESS username and ESS password")
    public void user_enters_ess_username_and_ess_password() {
        sendText(ConfigReader.read("MuserName"), driver.findElement(By.id(Constants.USERNAME_FIELD)));
        sendText(ConfigReader.read("Mpassword"), driver.findElement(By.id(Constants.PASSWORD_FIELD)));
        waitForElementToBeClickAble(loginPage.loginButton);
    }

    @When("user clicks on MyInfo option")
    public void user_clicks_on_my_info_option() {
        waitForElementToBeClickAble(personalDetailsPage.myInfoBtn);
    }

    @When("user clicks on the edit button in personal details page")
    public void user_clicks_on_the_edit_button_in_personal_details_page() {
       // getWait(personalDetailsPage.saveBtn);
        click(personalDetailsPage.saveBtn);

    }

    @Then("user updates {string}, {string} and {string} field")
    public void user_updates_and_field(String firstname, String middlename, String lastname) {
        personalDetailsPage.enterFirstName(firstname);
        personalDetailsPage.enterMiddleName(middlename);
        personalDetailsPage.enterLastName(lastname);
    }

    @Then("user updates {string} button")
    public void user_updates_button() {
        boolean stateofFRB = personalDetailsPage.femaleRBtn.isSelected();
        if (!stateofFRB) {
            click(personalDetailsPage.femaleRBtn);
        }
    }

    @And("user updates {string} and {string} field")
    public void userUpdatesAndField(String nationality, String maritalstatus) {
        Select select = new Select(personalDetailsPage.nationalityDD);
        select.selectByVisibleText(nationality);
        select.selectByVisibleText(maritalstatus);
    }

    @Then("user clicks on the save button")
    public void user_clicks_on_the_save_button() {
        personalDetailsPage.clickSaveButton();
    }

    @Then("the updated personal information is successfully saved")
    public void the_updated_personal_information_is_successfully_saved() {
        System.out.println("placeholder");
    }

    @Then("changes are verified through database")
    public void changes_are_verified_through_database() {
        System.out.println("placeholder");
    }

}
