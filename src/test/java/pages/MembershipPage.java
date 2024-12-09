package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class MembershipPage extends CommonMethods {

    //object repositories

    @FindBy(id="txtUsername")
    public WebElement usernameField;

    @FindBy(id="txtPassword")
    public WebElement passwordField;

    @FindBy(id="btnLogin")
    public WebElement loginButton;

    @FindBy(id="spanMessage")
    public WebElement errorMessage;

    public MembershipPage(){
        PageFactory.initElements(driver, this);
    }


}
