package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class PersonalDetailsPage extends CommonMethods {

    @FindBy (id = "menu_pim_viewMyDetails")
    public WebElement myInfoBtn;

    @FindBy(xpath = "//h1[text()='Personal Details']")
    public WebElement personalDetailsHeader;

    @FindBy(id = "employeeId")
    public WebElement employeeIdField;

    @FindBy(xpath = "//p//input[@value='Edit']")
    public WebElement saveBtn;

    @FindBy(id = "firstName")
    public WebElement firstNameField;

    @FindBy(id = "middleName")
    public WebElement middleNameField;

    @FindBy(id = "lastName")
    public WebElement lastNameField;

    @FindBy(xpath = "//*[@id='btnSave']")
    public WebElement editBtn;

    @FindBy (xpath = "//*[@id=\"frmEmpPersonalDetails\"]/fieldset/ol[3]/li[1]/label")
    public WebElement genderField;

    @FindBy(id = "//*[@id=\"personal_optGender_2\"]")
    public WebElement femaleRBtn;

    @FindBy (id="personal_cmbNation")
    public WebElement nationalityDD;

    @FindBy (id = "personal_cmbMarital")
    public WebElement maritalStatus;

    public PersonalDetailsPage() {
        PageFactory.initElements(driver, this);
    }

    public void enterEmployeeId(String employeeId) {
        sendText(employeeIdField, employeeId);
    }

    public void enterFirstName(String firstName) {
        sendText(firstNameField, firstName);
    }

    public void enterMiddleName(String middleName) {
        sendText(middleNameField, middleName);
    }

    public void enterLastName(String lastName) {
        sendText(lastNameField, lastName);
    }
    public  void clickSaveButton() {
        WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"btnSave\"]"));
        saveButton.click();
    }
    public void clickDependentsButton() {
        WebElement dependentsButton = driver.findElement(By.xpath("//a[normalize-space()='Dependents']"));
        dependentsButton.click();
    }
}