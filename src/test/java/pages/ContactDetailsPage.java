
package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class ContactDetailsPage extends CommonMethods {
    @FindBy(id = "contact_street1")
    public WebElement contactStreet1Field;

    @FindBy(id = "contact_street2")
    public WebElement contactStreet2Field;

    @FindBy(id = "contact_city")
    public WebElement contactCityField;

    @FindBy(xpath = "//select[@id='contact_state']")
    public WebElement contactProvinceField;

    @FindBy(id = "contact_emp_zipcode")
    public WebElement contactZipCodeField;

    @FindBy(xpath = "//select[@id='contact_country']")
    public WebElement contactCountryField;

    @FindBy(id = "contact_emp_hm_telephone")
    public WebElement contactHomeTelephoneField;

    @FindBy(id = "contact_emp_mobile")
    public WebElement contactMobilePhoneField;

    @FindBy(id = "contact_emp_work_telephone")
    public WebElement contactWorkTelephonePhoneField;

    @FindBy(id = "contact_emp_work_email")
    public WebElement contactWorkEmailField;

    @FindBy(id = "contact_emp_oth_email")
    public WebElement contactOtherEmailField;

    @FindBy(id="btnSave")
    public WebElement saveButton;

    @FindBy(xpath ="//a[text()='Contact Details']")
    public WebElement contactDetailsMenu;

    @FindBy(xpath = "//a[text()='34131422']")
    public WebElement idField;

    @FindBy(xpath = "//input[@value='Edit']")
    public WebElement editButton;


    public ContactDetailsPage(){
        PageFactory.initElements(driver, this);
    }


}