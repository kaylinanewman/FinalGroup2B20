package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class JobDetailsPage extends CommonMethods {


    @FindBy(xpath = "//a[text()='Will ms']")
    public WebElement nameField;

    @FindBy(xpath = "//*[@id=\"sidenav\"]/li[6]/a")
    public WebElement jobTab;

    @FindBy(xpath = "//input[@value='Edit']")
    public WebElement editButton;

    @FindBy(id = "job_job_title")
    public WebElement jobTitleDropdown;

    @FindBy(id = "job_emp_status")
    public WebElement employmentStatusDropdown;

    @FindBy(id = "job_joined_date")
    public WebElement joinedDateInput;

    @FindBy(id = "job_sub_unit")
    public WebElement subUnitDropdown;

    @FindBy(id = "job_location")
    public WebElement locationDropdown;

    @FindBy(id = "job_contract_start_date")
    public WebElement contractStartDateInput;

    @FindBy(id = "job_contract_end_date")
    public WebElement contractEndDateInput;

    @FindBy(id = "btnSave")
    public WebElement saveButton;

    public JobDetailsPage() {
        PageFactory.initElements(driver, this);
    }


}