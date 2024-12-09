package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.CommonMethods;
import utils.Constants;
import utils.DBReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddDependentSteps extends CommonMethods {

    public String newEmployeeID;

    @When("user creates a new employee by sending {string} and {string} and {string}")
    public void user_creates_a_new_employee_by_sending_and_and(String name, String middleName, String lastName) {
        addDependentPage.createEmployee(name, middleName, lastName);
    }

    @When("retrieves unique EmployeeID")
    public void retrieves_unique_employee_id() {
        newEmployeeID = addDependentPage.getEmployeeID();
        System.out.println("Generated Employee ID: " + newEmployeeID);
    }

    @When("clicks on Save button")
    public void clicks_on_save_button() {
        addDependentPage.clickSaveButton();
    }

    @When("user clicks on Add Employee option")
    public void user_clicks_on_add_employee_option() {
        addDependentPage.clickAddEmployeeOption();
    }

    @When("user enters employeeID {string} in respective field")
    public void user_enters_employee_id_in_respective_field(String empID) {
        addDependentPage.enterEmployeeID(empID);
    }

    @When("user clicks on Search button")
    public void user_clicks_on_search_button() {
        addDependentPage.clickSearchButton();
    }

    @When("user clicks on desired id number")
    public void user_clicks_on_desired_id_number() {
        addDependentPage.clickDesiredID();
    }

    @Then("user is navigated to Personal Details page")
    public void user_is_navigated_to_personal_details_page() {
        Assert.assertTrue(addDependentPage.isPersonalDetailsPageDisplayed());
    }

    @Then("user clicks on Dependents button")
    public void user_clicks_on_dependents_button() {
        addDependentPage.clickDependentsButton();
    }

    @Given("user clicks on Add button")
    public void user_clicks_on_add_button() {
        addDependentPage.clickAddButton();
    }

    @Then("user verifies that Name, Relationship and Date of Birth fields are displayed and editable")
    public void user_verifies_that_name_relationship_and_date_of_birth_fields_are_displayed_and_editable() {
        addDependentPage.verifyFieldsAreDisplayedAndEditable();
    }

    @When("user enters employeeID number")
    public void user_enters_employee_id_number() {
        addDependentPage.enterEmployeeID(newEmployeeID);
    }

    @When("user enters previously created employeeID number in Id tab")
    public void user_enters_previously_created_employee_id_number_in_id_tab() {
        addDependentPage.enterEmployeeID(newEmployeeID);
    }

    @Then("user enters {string}")
    public void user_enters(String name) {
        sendText(name, addDependentPage.dependentNameField);
        Constants.allNames.add(name);
    }

    @Then("user selects {string} from dropdown menu")
    public void user_selects_from_dropdown_menu(String relationship) {
        Select sel = new Select(addDependentPage.relationshipDropdown);
        sel.selectByVisibleText(relationship);
    }

    @Then("user selects date of birth {string} and {string} and {string}")
    public void user_selects_date_of_birth_and_and(String year, String month, String day) {
        addDependentPage.selectDateOfBirth(year, month, day);
    }

    @Then("user clicks on Save button")
    public void user_clicks_on_save_button() {
        addDependentPage.clickSaveButton();
    }

    @Then("dependent is successfully saved")
    public void dependent_is_successfully_saved() {
        Assert.assertTrue(addDependentPage.successfullySavedCloseButton.isDisplayed());
    }

    @Then("the HRMS application should clearly display the list of dependents added by the employee")
    public void the_hrms_application_should_clearly_display_the_list_of_dependents_added_by_the_employee() {
        List<WebElement> allDependents = driver.findElements(By.xpath(Constants.allDependentsNameXPath));
        ArrayList<String> allDependentsText = new ArrayList<>();
        allDependents.forEach(dependent -> allDependentsText.add(dependent.getText().trim()));

        Assert.assertEquals(allDependentsText, Constants.allNames);
    }

    @Then("changes are verified through database using {string}")
    public void changes_are_verified_through_database_using(String name) {
        List<Map<String, String>> data = DBReader.fetch("SELECT ed_name FROM hs_hr_emp_dependents WHERE emp_number = " + newEmployeeID);
        String dbDependentName = data.get(0).get("ed_name");

        Assert.assertEquals(name, dbDependentName);
    }

    @Then("user can see {string} error message")
    public void user_can_see_error_message(String errorMessage) {
        String actualMessage = addDependentPage.requiredMessage.getText();
        Assert.assertEquals(errorMessage, actualMessage);
    }

    @Then("user can see {string} message")
    public void user_can_see_message(String message) {
        String actualMessage = addDependentPage.relationshipRequiredMessage.getText();
        Assert.assertEquals(message, actualMessage);
    }

    @Then("selects the {string} of the dependent to be deleted")
    public void selects_the_of_the_dependent_to_be_deleted(String name) {
        List<WebElement> allDependents = driver.findElements(By.xpath(Constants.allDependentsNameXPath));
        int count = 1;
        for (WebElement dependent : allDependents) {
            if (dependent.getText().equals(name)) {
                WebElement checkBox = driver.findElement(By.xpath("//table/tbody/tr[" + count + "]/td[1]"));
                click(checkBox);
                break;
            }
            count++;
        }
    }

    @Then("deletes the dependent from list")
    public void deletes_the_dependent_from_list() {
        click(addDependentPage.deleteButton);
    }

    @Then("dependent is successfully deleted")
    public void dependent_is_successfully_deleted() {
        Assert.assertTrue(addDependentPage.successfullyDeletedCloseButton.isDisplayed());
    }

    @Then("user selects the {string} of the dependent to be edited")
    public void user_selects_the_of_the_dependent_to_be_edited(String name) {
        List<WebElement> allDependents = driver.findElements(By.xpath(Constants.allDependentsNameXPath));
        int count = 1;
        for (WebElement dependent : allDependents) {
            if (dependent.getText().equals(name)) {
                WebElement nameLink = driver.findElement(By.xpath("//table/tbody/tr[" + count + "]/td[2]/a"));
                click(nameLink);
                break;
            }
            count++;
        }

        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Edit Dependent']")).isDisplayed());
    }

    @Then("user selects {string} from dropdown menu to edit")
    public void user_selects_from_dropdown_menu_to_edit(String relationship) {
        Select sel = new Select(addDependentPage.relationshipTab);
        sel.selectByValue(relationship);
    }

    @Then("user {string}")
    public void user(String relationship) {
        addDependentPage.specifyRelationshipTab.sendKeys(relationship);
    }

    @Then("user saves the changes successfully")
    public void user_saves_the_changes_successfully() {
        addDependentPage.clickSaveButton();
        Assert.assertTrue(addDependentPage.successfullySavedCloseButton.isDisplayed());
    }
}
