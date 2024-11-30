package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.APIPayload;

import static utils.APIConstants.BASE_URI;
import static utils.APIConstants.GENERATE_TOKEN_ENDPOINT;

public class APIAuthenticationPage {
    private final String email;
    private final String password;

    public APIAuthenticationPage(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Response requestAuthenticationToken(String email, String password) {
        APIPayload payload = new APIPayload(email, password);

        // Make the POST request to get the authentication token
        Response response = RestAssured.given()
                .baseUri(BASE_URI)
                .basePath(GENERATE_TOKEN_ENDPOINT)
                .contentType("application/json")
                .body(payload)
                .post();
        if (response == null) {
            throw new IllegalStateException("Response is null. Check the API request and endpoint.");
        }

        return response;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
