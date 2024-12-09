package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.IsEqual;
import utils.APIConstants;
import utils.APIPayloadConstants;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class DeleteEmployeeAPISteps {
    Response response;
    private RequestSpecification request;
    public static String employee_id;
    public static String token;
    public static String delEmpID;

    @Given("generate JWT bearer token")
    public void generate_jwt_bearer_token() {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
                body("{\n" +
                        "  \"email\": \"ira34@gmail.com\",\n" +
                        "  \"password\": \"Fall2024\"\n" +
                        "}");

        response = request.when().post(APIConstants.GENERATE_TOKEN);

        token = "Bearer "+ response.jsonPath().getString("token");
        System.out.println(token);
    }
    @Given("a request is prepared for creating the employee with dynamic json payload usgin data {string} , {string} , {string} , {string} , {string} , {string} , {string}")
    public void a_request_is_prepared_usgin_data
            (String firstname, String lastname, String middlename,
             String gender, String birthday, String status,
             String jobtitle) {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,
                        APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY,token).
                body(APIPayloadConstants.createEmployeeJsonPayloadDynamic
                        (firstname,lastname,middlename,
                                gender,birthday,status,jobtitle));
    }
    @When("a POST call request is made to create the employee")
    public void a_post_call_request_is_made_to_create_the_employee() {
        response = request.when().post(APIConstants.CREATE_EMPLOYEE);
    }
    @Then("the resulting status code for this request is {int}")
    public void the_resulting_status_code_for_this_request_is(Integer int1) {
        response.then().assertThat().statusCode(int1);
    }
    @And("the employee id {string} is stored and values are validated")
    public void the_employee_id_is_stored_and_values_are_validated(String empPath) {

        employee_id = response.jsonPath().getString(empPath);
        System.out.println("The employee id is: " + employee_id);
        response.then().assertThat().
                body("Employee.emp_firstname",equalTo("irina"));
    }
    @When("a request is prepared for deleting the employee")
    public void a_request_is_prepared_for_deleting_the_employee() {
        request = given().header(APIConstants.HEADER_AUTHORIZATION_KEY,token).
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
                queryParam("employee_id",employee_id);
        System.out.println("requested ID is: "+ employee_id);
    }
    @And("a delete call is made to remove the employee")
    public void a_delete_call_is_made_to_remove_the_employee() {
        response = request.when().delete(APIConstants.DELETE_USER);
    }
    @Then("the status code for the delete request is {int}")
    public void the_status_code_for_the_delete_request_is(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
        System.out.println("Response status code: " + response.getStatusCode());
    }
    @Then("the response message for deletion is {string} and includes deleted employee details")
    public void the_response_message_for_deletion_is_and_includes_deleted_employee_details(String string) {
        response.then().assertThat().body("message", IsEqual.equalTo("Employee deleted"));
        System.out.println("Response body: " + response.getBody().asString());
    }
    @Given("a request is prepared for deleting employee with non-existent employee ID")
    public void a_request_is_prepared_for_deleting_employee_with_non_existent_employee_id() {
        delEmpID="116117A";
        // Use the same employee_id deleted in Scenario 1
        request = given()
                .header(APIConstants.HEADER_AUTHORIZATION_KEY,token)
                .queryParam("employee_id", delEmpID);
    }
    @When("a DELETE call is made to remove the employee with non-existent employee ID")
    public void a_delete_call_is_made_to_remove_the_employee_with_non_existent_employee_id() {
        response = request.when().delete(APIConstants.DELETE_USER);
    }
    @Then("the error status code for the delete request is {int}")
    public void the_error_status_code_for_the_delete_request_is(Integer expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
        System.out.println("Response status code: " + response.getStatusCode());
    }

    @Then("the error message for non-existent employee is {string}")
    public void the_error_message_for_non_existent_employee_is(String expectedMessage) {
        response.then().body("Massage", equalTo(expectedMessage));
        System.out.println("Response body: " + response.getBody().asString());
    }
    @Given("a request is prepared without employee ID parameter for deletion")
    public void a_request_is_prepared_without_employee_id_parameter_for_deletion() {
        // Prepare the request without the employee_id parameter
        request = given()
                .header(APIConstants.HEADER_AUTHORIZATION_KEY,token); // No employee_id parameter included
    }
    @When("a DELETE call is made to remove the employee without employee ID")
    public void a_delete_call_is_made_to_remove_the_employee_without_employee_id() {
        response = request.when().delete(APIConstants.DELETE_USER);;
    }


    @Then("the error message for missing employee ID is {string}")
    public void the_error_message_for_missing_employee_id_is(String expectedMessage) {
        response.then().body("Error", equalTo(expectedMessage));
        System.out.println("Response body: " + response.getBody().asString());
    }
}

