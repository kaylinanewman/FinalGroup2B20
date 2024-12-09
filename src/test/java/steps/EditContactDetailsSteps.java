
package steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonMethods;



import java.time.Duration;




public class EditContactDetailsSteps extends CommonMethods {
    String addressStreet1;
    String addressStreet2;
    String city;
    String state;
    String zipCode;
    String country;
    String homePhone;
    String mobilePhone;
    String workPhone;
    String workEmail;
    String otherEmail;


    @Then("user clicks the Contact Details section")
    public void user_clicks_the_contact_details_section() {
        click(contactDetailsPage.contactDetailsMenu);
    }


    @When("user clicks on edit button")
    public void user_clicks_on_edit_button() {
        click(contactDetailsPage.editButton);
    }

    @And("user updates his contact information with the following details: {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string}")
    public void user_updates_his_common_information_with_the_following_details_and_and_and_and_and_and_and_and_and_and(
            String addressStreet1, String addressStreet2, String city, String state,
            String zipCode, String country, String homePhone, String mobilePhone,
            String workPhone, String workEmail, String otherEmail) throws InterruptedException {


        sendText(addressStreet1, contactDetailsPage.contactStreet1Field);
        sendText(addressStreet2, contactDetailsPage.contactStreet2Field);
        sendText( city,contactDetailsPage.contactCityField);
        sendText(zipCode, contactDetailsPage.contactZipCodeField);
        selectDropdownOption(contactDetailsPage.contactCountryField,country);
        selectDropdownOption(contactDetailsPage.contactProvinceField,state);
        sendText(homePhone,contactDetailsPage.contactHomeTelephoneField);
        sendText(mobilePhone,contactDetailsPage.contactMobilePhoneField);
        sendText(workPhone,contactDetailsPage.contactWorkTelephonePhoneField);
        sendText(workEmail,contactDetailsPage.contactWorkEmailField);
        sendText(otherEmail,contactDetailsPage.contactOtherEmailField);

        click(contactDetailsPage.saveButton);
    }


    @Then("the contact information should be updated successfully with the following details: {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string}")
    public void the_contact_information_should_be_updated_successfully_with_the_following_details_and_and_and_and_and_and_and_and_and_and(String addressStreet1, String addressStreet2, String city, String state, String zipCode, String country, String homePhone, String mobilePhone, String workPhone, String workEmail, String otherEmail) {

        addressStreet1= getAttribute(contactDetailsPage.contactStreet1Field, "value");
        addressStreet2 = getAttribute(contactDetailsPage.contactStreet2Field, "value");
        city = getAttribute(contactDetailsPage.contactCityField, "value");
        state = getAttribute(contactDetailsPage.contactProvinceField, "value");
        zipCode = getAttribute(contactDetailsPage.contactZipCodeField, "value");
        country = getAttribute(contactDetailsPage.contactCountryField, "value");
        homePhone = getAttribute(contactDetailsPage.contactHomeTelephoneField, "value");
        mobilePhone = getAttribute(contactDetailsPage.contactMobilePhoneField, "value");
        workPhone = getAttribute(contactDetailsPage.contactWorkTelephonePhoneField, "value");
        workEmail = getAttribute(contactDetailsPage.contactWorkEmailField, "value");
        otherEmail = getAttribute(contactDetailsPage.contactOtherEmailField, "value");




        Assert.assertEquals(addressStreet1, addressStreet1);
        Assert.assertEquals(addressStreet2, addressStreet2);
        Assert.assertEquals(city, city);
        Assert.assertEquals(state,state);
        Assert.assertEquals(zipCode,zipCode);
        Assert.assertEquals(country,country);
        Assert.assertEquals(homePhone,homePhone);
        Assert.assertEquals(mobilePhone, mobilePhone);
        Assert.assertEquals(workPhone, workPhone);
        Assert.assertEquals(workEmail, workEmail);
        Assert.assertEquals(otherEmail, otherEmail);
    }

}
