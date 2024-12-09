package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pages.APIStepsClassPage;
import utils.APIConstants;
import utils.APIPayloadConstants;

import static org.hamcrest.Matchers.equalTo;

public class APIStepsClass {

    public static String employee_id;
    public static String error_message;
    public static String token;
    APIStepsClassPage apiPage = new APIStepsClassPage();
    public static String message_respond;
    Response response;
    private RequestSpecification request;


    @Then("the {string} {string} is stored and values are validated")
    public void the_is_stored_and_values_are_validated(String errorPath, String msgPath) {
        error_message=response.jsonPath().getString(errorPath);
        message_respond=response.jsonPath().getString(msgPath);

        System.out.println("The error is: "+error_message);
        System.out.println("The message is:"+message_respond);
        response.then().assertThat().body(APIConstants.ERROR,equalTo(error_message));
        response.then().assertThat().body(APIConstants.MESSAGE,equalTo(message_respond));

    }
    @Given("a JWT bearer token is generated")
    public void a_jwt_bearer_token_is_generated() {
        response = apiPage.generateToken(apiPage.prepareTokenRequest());
        token = APIConstants.BEARER + response.jsonPath().getString(APIConstants.TOKEN_PATH);
        System.out.println(token);
    }

    @Given("a JWT bearer token is generated using {string} and {string}")
    public void a_jwt_bearer_token_is_generated_using_and(String email, String password) {
        response = apiPage.generateToken(apiPage.prepareDynamicTokenRequest(email, password));
        token = APIConstants.BEARER + response.jsonPath().getString(APIConstants.TOKEN_PATH);
    }

    @When("creating a token")
    public void creating_a_token() {
        response = apiPage.generateToken(apiPage.prepareTokenRequest());
        token = APIConstants.BEARER + response.jsonPath().getString(APIConstants.TOKEN_PATH);
    }

    @Given("a request is prepared for creating a user with {string} and {string} and {string}")
    public void a_request_is_prepared_for_creating_a_user_with_and_and(String name, String email, String password) {
        request = apiPage.prepareCreateUserRequest(name, email, password);
    }

    @When("a POST call is made to create the user")
    public void a_post_call_is_made_to_create_the_user() {
        response = apiPage.createUser(request);
    }

    @Then("the {string} for this request is verified")
    public void the_status_code_is_verified(String statusCode) {
        apiPage.validateStatusCode(response, Integer.parseInt(statusCode));
    }

    @Then("the respond {string} is stored and values are validated")
    public void the_response_message_is_validated(String message) {
        apiPage.validateResponseMessage(response, message);
    }

    @Given("a request is prepared for creating the employee")
    public void a_request_is_prepared_for_creating_the_employee() {
        request = apiPage.prepareCreateEmployeeRequest(token, APIPayloadConstants.createEmployeePayload());
    }

    @When("a POST call is made to create the employee")
    public void a_post_call_is_made_to_create_the_employee() {
        System.out.println("Request payload for creating employee:");
        request.log().all();
        response = apiPage.createEmployee(request);
        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody().asString());
        employee_id = response.jsonPath().getString(APIConstants.RESPONSE_EMPLOYEE_ID_PATH);
    }

    @Then("the status code for this request is {int}")
    public void the_status_code_for_this_request_is(Integer expectedStatusCode) {
        apiPage.validateStatusCode(response, expectedStatusCode);
    }

    @Then("the employee id {string} is stored and values are validated")
    public void the_employee_id_is_stored_and_validated(String path) {
        employee_id = response.jsonPath().getString(path);
        response.then().assertThat().body(APIConstants.RESPONSE_EMPLOYEE_ID_PATH, equalTo(employee_id));
    }

    @Given("a request is prepared for creating the employee with incorrect birthday field format")
    public void a_request_is_prepared_for_creating_the_employee_with_incorrect_birthday_field_format() {
        request = apiPage.prepareCreateEmployeeRequest(token, APIPayloadConstants.createEmployeePayloadIncorrectBDayFormat());
    }

    @Then("the message {string} is stored and values are validated")
    public void the_message_is_stored_and_validated(String errorPath) {
        error_message = response.jsonPath().getString(errorPath);
        response.then().assertThat().body(APIConstants.ERROR, equalTo(error_message));
    }

    @Given("a request is prepared for creating the employee with invalid gender field")
    public void a_request_is_prepared_for_creating_the_employee_with_invalid_gender_field() {
        request = apiPage.prepareCreateEmployeeRequest(token, APIPayloadConstants.createEmployeePayloadInvalidGenderField());
    }

    @Then("the error {string} for invalid gender is stored and values are validated")
    public void the_error_message_is_validated(String error) {
        error_message = response.jsonPath().getString(error);
        response.then().assertThat().body(APIConstants.MESSAGE, equalTo(error_message));
    }

    @Given("a request is prepared for creating the employee with empty field")
    public void a_request_is_prepared_for_creating_the_employee_with_empty_field() {
        request = apiPage.prepareCreateEmployeeRequest(token, APIPayloadConstants.createEmployeePayloadEmptyField());
    }

    @Then("the response {string} is stored and validated")
    public void the_response_is_stored_and_validated(String errorPath) {
        error_message = response.jsonPath().getString(errorPath);
        response.then().assertThat().body(APIConstants.ERROR, equalTo(error_message));
    }

    @Given("a request is prepared for creating the employee using {string}, {string}, {string},{string}, {string},{string},{string}")
    public void a_request_is_prepared_for_creating_the_employee_using
            (String firstname, String lastname, String middlename,
             String gender, String birthday, String status,
             String jobtitle) {

        request = request.given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,
                        APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY,token).
                body(APIPayloadConstants.createEmployeeJsonPayloadDynamic
                        (firstname,lastname,middlename,
                                gender,birthday,status,jobtitle));
    }
}
