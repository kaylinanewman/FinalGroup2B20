package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class AddEmployeePage extends CommonMethods {
    @FindBy(id = "btnSave")
    public WebElement saveButton;

    @FindBy(id = "firstName")
    public WebElement firstnameLocator;

    @FindBy(id = "middleName")
    public WebElement middlenameLocator;

    @FindBy(id = "lastName")
    public WebElement lastnameLocator;

    @FindBy(id = "searchSystemUser_userName")
    public WebElement usernameTxtBox;

    @FindBy(id = "searchBtn")
    public WebElement searchBtn;

    @FindBy(xpath = "//table[@id='resultTable']/tbody/tr/td[@class='left']/a")
    public WebElement searchResult;

    @FindBy(id = "error")
    public WebElement error;

    @FindBy(id = "lastNameError")
    public WebElement lastNameError;

    public AddEmployeePage() {
        PageFactory.initElements(driver, this);
    }
}


