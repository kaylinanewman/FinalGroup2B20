package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;


public class MembershipSteps extends CommonMethods {

    @When("the user clicks on the MyInfo option")
    public void the_user_clicks_on_the_my_info_option() {
        WebElement Myinfo= driver.findElement(By.xpath("//a[@id='menu_pim_viewMyDetails']"));
        Myinfo.click();
    }
    @When("the user clicks on Membership")
    public void the_user_clicks_on_membership() throws InterruptedException {
        WebElement membership=driver.findElement(By.xpath("//a[@href='/humanresources/symfony/web/index.php/pim/viewMemberships/empNumber/102382']"));
        membership.click();
        Thread.sleep(1000);

    }
    @When("the user clicks on Add to assigned Membership")
    public void the_user_clicks_on_add_to_assigned_membership() throws InterruptedException {
        WebElement addID= driver.findElement(By.xpath("//input[@id='btnAddMembershipDetail']"));
        addID.click();
        Thread.sleep(1000);

    }
    @When("the user selects a  membership")
    public void the_user_selects_a_membership() throws InterruptedException {
        WebElement Membership= driver.findElement (By.xpath("//select[@id='membership_membership']"));
        Membership.click();
        Select sel=new Select(Membership);
        sel.selectByIndex(1);
        Thread.sleep(1000);

    }


    @When("the user selects the Subscription Paid By option")
    public void the_user_selects_the_subscription_paid_by_option() throws InterruptedException {
        WebElement Subscription= driver.findElement(By.xpath("//select[@id='membership_subscriptionPaidBy']"));
        Subscription.click();
        Select sel1=new Select(Subscription);
        sel1.selectByValue("Individual");
        Thread.sleep(1000);

    }


    @When("the user enters the  subscription amount")
    public void the_user_enters_the_subscription_amount() throws InterruptedException {
        WebElement amount= driver.findElement(By.xpath("//input[@id='membership_subscriptionAmount']"));
        amount.sendKeys("1000");
        Thread.sleep(1000);

    }


    @When("the user selects the  currency for the subscription")
    public void the_user_selects_the_currency_for_the_subscription() throws InterruptedException {
        WebElement currency= driver.findElement(By.xpath("//select[@id='membership_currency']"));
        currency.click();
        Select sel2=new Select(currency);
        sel2.selectByValue("EGP");
        Thread.sleep(1000);

    }



    @When("the user selects the  subscription date")
    public void the_user_selects_the_subscription_date() throws InterruptedException {
        WebElement commenceDay= driver.findElement (By.xpath("//input[@id='membership_subscriptionCommenceDate']"));
        commenceDay.click();
        Thread.sleep(1000);


        driver.findElements(By.xpath("(//img[@class='ui-datepicker-trigger'])[1]"));
        WebElement month= driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
        Select sel3=new Select(month);
        sel3.selectByVisibleText("Feb");
        Thread.sleep(1000);


        WebElement year= driver.findElement(By.xpath("//select[@class='ui-datepicker-year']"));
        Select sel4=new Select(year);
        sel4.selectByVisibleText("2022");
        Thread.sleep(1000);


        List<WebElement> dates= driver.findElements(By.xpath("(//a[@class='ui-state-default'])[5]"));
        for (WebElement date: dates){
            if(date.getText().equals("5")){
                date.click();}
            Thread.sleep(1000);

        }
    }


    @When("the user selects the  renewal date")
    public void the_user_selects_the_renewal_date() throws InterruptedException {
        WebElement renewalDay= driver.findElement (By.xpath("//input[@id='membership_subscriptionRenewalDate']"));
        renewalDay.click();
        WebElement month1= driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
        Select sel5=new Select(month1);
        sel5.selectByVisibleText("Nov");
        WebElement year2= driver.findElement(By.xpath("//select[@class='ui-datepicker-year']"));
        Select sel6=new Select(year2);
        sel6.selectByVisibleText("2024");
        List<WebElement> dates1= driver.findElements(By.xpath("(//a[@class='ui-state-default'])[6]"));
        for (WebElement date1: dates1) {
            if (date1.getText().equals("6")) {
                date1.click();
                Thread.sleep(2000);

            }
        }
    }


    @Then("the user sees a success message Membership saved successfully")
    public void the_user_sees_a_success_message_membership_saved_successfully() throws InterruptedException {
        WebElement saveBtn=driver.findElement(By.xpath("//input[@name='btnSaveMembership']"));
        saveBtn.click();
        Thread.sleep(3000);
    }
}
