package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class DependentsListPage extends CommonMethods {

    @FindBy(xpath = "//h1[text()='Dependents']")
    public WebElement dependentsHeader;

    @FindBy(id = "dependentList")
    public WebElement dependentListTable;

    public DependentsListPage() {
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getDependentsList() {
        return dependentListTable.findElements(By.tagName("tr"));
    }

    public void clickAddButton() {
        WebElement addButton = driver.findElement(By.xpath("//input[@id='btnAddAttachment']"));
        addButton.click();
    }
}
