package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;


public class DashboardPage extends CommonMethods {

    @FindBy(id = "welcome")
    public WebElement welcomeText;
    @FindBy(css = "a[href*='/humanresources/symfony/web/index.php/auth/logout']")
    public WebElement logoutOption;
    @FindBy(id = "menu_admin_viewAdminModule")
    public WebElement adminOption;
    @FindBy(id = "menu_pim_viewPimModule")
    public WebElement pimOption;
    @FindBy(css = "#dashboard-header")
    private WebElement dashboardHeader;
    @FindBy(css = "#dashboard-content")
    private WebElement dashboardContent;
    @FindBy(css = "#dashboard-footer")
    private WebElement dashboardFooter;

    public DashboardPage() {
        PageFactory.initElements(driver, this);
    }
}

