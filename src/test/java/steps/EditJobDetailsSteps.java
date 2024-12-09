package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonMethods;

import java.time.Duration;
import java.util.Map;

public class EditJobDetailsSteps extends CommonMethods {

    @When("user clicks on name field")
    public void user_clicks_on_name_field() {
        click(jobDetailsPage.nameField);
    }

    @Then("the admin user clicks on the Job tab")
    public void the_admin_user_clicks_on_the_job_tab() {
        click(jobDetailsPage.jobTab);
    }

    @When("the admin user clicks the Edit button")
    public void the_admin_user_clicks_on_edit_button() {
        //  WebElement editButton= driver.findElement(By.xpath("//input[@value='Edit']"));
        click(jobDetailsPage.editButton);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Then("the admin user should be able to edit the following fields:")
    public void the_admin_user_should_be_able_to_edit_the_following_fields(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        selectDropdownOption(jobDetailsPage.jobTitleDropdown, data.get("Job Title"));
        selectDropdownOption(jobDetailsPage.employmentStatusDropdown, data.get("Employment Status"));
        selectDropdownOption(jobDetailsPage.subUnitDropdown, data.get("Sub Unit"));
        selectDropdownOption(jobDetailsPage.locationDropdown, data.get("Location"));

        sendText(jobDetailsPage.joinedDateInput, data.get("Joined Date"));
        sendText(jobDetailsPage.contractStartDateInput, data.get("Start Date"));
        sendText(jobDetailsPage.contractEndDateInput, data.get("End Date"));
    }

    @And("the admin user clicks the Save button")
    public void the_admin_user_clicks_the_save_button() {
        click(jobDetailsPage.saveButton);
    }

}