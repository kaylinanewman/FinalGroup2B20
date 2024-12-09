package pages;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.CommonMethods;

import java.util.List;

public class AddDependentPage extends CommonMethods {

    @FindBy(id = "menu_pim_viewEmployeeList")
    public WebElement employeeListOption;

    @FindBy(id = "empsearch_id")
    public WebElement employeeIDField;

    @FindBy(id = "searchBtn")
    public WebElement searchBtn;

    @FindBy(xpath = "//table/tbody/tr/td[2]")
    public WebElement desiredId;

    @FindBy(xpath = "//a[text()='Dependents']")
    public WebElement dependentsButton;

    @FindBy(xpath = "//h1[text()='Personal Details']")
    public WebElement personalDetailsDisplay;

    @FindBy(id = "btnAddDependent")
    public WebElement addButton;

    @FindBy(css = "#dependent_name")
    public WebElement dependentNameField;

    @FindBy(id = "dependent_relationshipType")
    public WebElement relationshipDropdown;

    @FindBy(xpath = "//select[@class='ui-datepicker-month']")
    public WebElement monthDropdown;

    @FindBy(xpath = "//select[@class='ui-datepicker-year']")
    public WebElement yearDropdown;

    @FindBy(id = "btnSaveDependent")
    public WebElement saveButton;

    @FindBy(xpath = "//a[@class='messageCloseButton']")
    public WebElement successfullySavedCloseButton;

    @FindBy(id = "dependent_list")
    public WebElement createdDependentList;

    @FindBy(xpath = "//span[text()='Required']")
    public WebElement requiredMessage;

    @FindBy(xpath = "//*[@id='frmEmpDependent']/fieldset/ol/li[2]/span")
    public WebElement relationshipRequiredMessage;

    @FindBy(id = "delDependentBtn")
    public WebElement deleteButton;

    @FindBy(xpath = "//*[@id='listing']/div[2]/div/a")
    public WebElement successfullyDeletedCloseButton;

    @FindBy(xpath = "//table/tbody/tr[count]/td[1]")
    public WebElement selectDependentCheckBox;

    @FindBy(css = "#dependent_relationshipType")
    public WebElement relationshipTab;

    @FindBy(css = "#dependent_dateOfBirth")
    public WebElement dobTab;

    @FindBy(id = "firstName")
    public WebElement employeeFirstName;

    @FindBy(id = "middleName")
    public WebElement employeeMiddleName;

    @FindBy(id = "lastName")
    public WebElement employeeLastName;

    @FindBy(id = "employeeId")
    public WebElement employeeID;

    @FindBy(id = "btnSave")
    public WebElement saveEmployeeButton;

    @FindBy(id = "menu_pim_addEmployee")
    public WebElement addEmployeeButton;

    @FindBy(id = "dependent_relationshipType")
    public Alert specifyRelationshipTab;

    public AddDependentPage() {
        PageFactory.initElements(driver, this);
    }

    public void createEmployee(String firstName, String middleName, String lastName) {
        sendText(employeeFirstName, firstName);
        sendText(employeeMiddleName, middleName);
        sendText(employeeLastName, lastName);
        click(saveEmployeeButton);
    }

    public String getEmployeeID() {
        return employeeID.getAttribute("value");
    }

    public void clickSaveButton() {
        click(saveButton);
    }

    public void clickAddEmployeeOption() {
        click(addEmployeeButton);
    }

    public void enterEmployeeID(String empID) {
        sendText(employeeIDField, empID);
    }

    public void clickSearchButton() {
        click(searchBtn);
    }

    public void clickDesiredID() {
        click(desiredId);
    }

    public boolean isPersonalDetailsPageDisplayed() {
        return personalDetailsDisplay.isDisplayed();
    }

    public void clickDependentsButton() {
        click(dependentsButton);
    }

    public void clickAddButton() {
        click(addButton);
    }

    public void verifyFieldsAreDisplayedAndEditable() {
        waitForElementToBeVisible(dependentNameField);
        Assert.assertTrue(dependentNameField.isDisplayed());
        Assert.assertTrue(dependentNameField.isEnabled());

        waitForElementToBeVisible(relationshipTab);
        Assert.assertTrue(relationshipTab.isDisplayed());
        Assert.assertTrue(relationshipTab.isEnabled());

       waitForElementToBeVisible(dobTab);
        Assert.assertTrue(dobTab.isDisplayed());
        Assert.assertTrue(dobTab.isEnabled());
    }

    public void enterDependentDetails(String name, String relationship, String year, String month, String day) {
        sendText(dependentNameField, name);
        selectDropdownValue(relationshipDropdown, relationship);
        selectDateOfBirth(year, month, day);
        click(saveButton);
    }

    public void selectDropdownValue(WebElement dropdown, String value) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(value);
    }

    public void selectDateOfBirth(String year, String month, String day) {
        click(dobTab);
        selectDropdownValue(monthDropdown, month);
        selectDropdownValue(yearDropdown, year);
        selectDateFromCalendar(day);
    }

    public void selectDateFromCalendar(String day) {
        List<WebElement> dates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr/td"));
        for (WebElement date : dates) {
            if (date.getText().equals(day)) {
                date.click();
                break;
            }
        }
    }
}
