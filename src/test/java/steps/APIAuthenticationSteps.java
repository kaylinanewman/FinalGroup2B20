package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pages.APIAuthenticationPage;
import utils.APICommonMethods;
import utils.APIConstants;
import utils.APIPayload;

import java.util.List;
import java.util.Map;

public class APIAuthenticationSteps {
    private APIAuthenticationPage apiAuthenticationPage;
    private Response response;

    @Given("I have the following credentials:")
    public void i_have_the_following_credentials(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String email = row.get("Email");
            String password = row.get("Password");
            APIPayload payload = new APIPayload(email, password);
            apiAuthenticationPage = new APIAuthenticationPage(email, password);
        }
        System.out.println("Credentials set: " + apiAuthenticationPage.email() + ", " + apiAuthenticationPage.password());
    }

    @When("I request an authentication token")
    public void i_request_an_authentication_token() {
        response = apiAuthenticationPage.requestAuthenticationToken(apiAuthenticationPage.email(), apiAuthenticationPage.password());
    }

    @Then("I should receive a JWT token in the response {}")
    public void iShouldReceiveAJWTTokenInTheResponse(String arg0) {
        APICommonMethods.validateJWTToken(response);
    }

    @Then("I should receive an error message Email or Password is incorrect")
    public void i_should_receive_an_error_message_email_or_password_is_incorrect() {
        APICommonMethods.validateErrorMessage(response, APIConstants.ERROR_EMAIL_OR_PASSWORD_INCORRECT); // Using common method to validate error message
    }

    @Then("I should receive an error message Please fill all inputs")
    public void i_should_receive_an_error_message_please_fill_all_inputs() {
        APICommonMethods.validateErrorMessage(response, APIConstants.ERROR_FILL_ALL_INPUTS);
    }
}
