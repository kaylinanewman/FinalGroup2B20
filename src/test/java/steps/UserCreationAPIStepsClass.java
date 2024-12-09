package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.CreateUserApiPayloadConstants;

import static io.restassured.RestAssured.given;


public class UserCreationAPIStepsClass {
    public static String message_respond;
    public static String error_message;
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    RequestSpecification request;
    Response response;

    @Given("a request is prepared for creating the user using {string}, {string} and {string}")
    public void a_request_is_prepared_for_creating_the_user_using_and
            (String name, String email, String password) {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,
                        APIConstants.HEADER_CONTENT_TYPE_VALUE).
                body(CreateUserApiPayloadConstants.createUserJsonDynamic(name, email, password));
    }

    @When("a POST call is made to create the user with invalid email")
    public void a_post_call_is_made_to_create_the_user_with_invalid_email() {
        response = request.when().post(APIConstants.CREATE_USER);
    }

    @Then("the {string} {string} and {string} {string} is stored and values are validated")
    public void the_and_is_stored_and_values_are_validated(String condition, String error, String data, String invEmail) {
        error_message = response.jsonPath().getString(condition);
        System.out.println(error_message);
        message_respond = response.jsonPath().getString(data);
        System.out.println(message_respond);

        Assert.assertEquals(error, error_message);
        Assert.assertEquals(invEmail, message_respond);
    }

    @Then("the status code for this invalid email request is {int}")
    public void the_status_code_for_this_invalid_email_request_is(Integer int1) {
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, (int) int1);
    }

    @Then("the {string} {string} for this request is stored and values are validated")
    public void the_for_this_request_is_stored_and_values_are_validated(String message, String errorMessage) {
        error_message = response.jsonPath().getString(message);
        Assert.assertEquals(errorMessage, error_message);
    }

    @When("a POST call is made to create the user with missing data")
    public void a_post_call_is_made_to_create_the_user_with_missing_data() {
        response = request.when().post(APIConstants.CREATE_USER);
    }

    @Then("the status code for this invalid data request is {int}")
    public void the_status_code_for_this_invalid_data_request_is(Integer int1) {
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, (int) int1);
    }
    @Then("the respond {string} for creating a user is stored and values are validated")
    public void the_respond_for_creating_a_user_is_stored_and_values_are_validated(String message) {
        message_respond=response.jsonPath().getString("Message");
        System.out.println("The message response is: "+message_respond);
        Assert.assertTrue(message.equals(message_respond));


    }
}
