package utils;

import io.restassured.RestAssured;

public class APIConstants {

    public static final String BASE_URI = APIConfigReader.getBaseURI();
    public static final String GENERATE_TOKEN_ENDPOINT = "/generateToken.php";
    public static final String EMAIL_PARAM = "email";
    public static final String PASSWORD_PARAM = "MPassword";

    public static final String ERROR_EMAIL_OR_PASSWORD_INCORRECT = "Email or Password is incorrect.";
    public static final String ERROR_FILL_ALL_INPUTS = "Please fill all inputs";

    //josely
    public static final String baseURI = RestAssured.baseURI
            = "http://hrm.syntaxtechs.net/syntaxapi/api";


    public static final String CREATE_EMPLOYEE = baseURI + "/createEmployee.php";
    public static final String GENERATE_TOKEN = baseURI + "/generateToken.php";
    public static final String GET_ONE_EMPLOYEE = baseURI + "/getOneEmployee.php";
    public static final String UPDATE_EMPLOYEE = baseURI + "/updateEmployee.php";
    public static final String CREATE_USER = baseURI + "/createUser.php";

    public static final String BEARER = "Bearer ";
    public static final String TOKEN_PATH = "token";
    public static final String MESSAGE = "Message";
    public static final String ERROR = "Error";
    public static final String INVALID_DATE_MESSAGE = "Please Enter a Valid Date Format. Example \"yyyy-mm-dd\"";
    public static final String INVALID_GENDER_MESSAGE = "Please Enter a Valid Date Format. Example \"yyyy-mm-dd\"";

    public static final String RESPONSE_EMPLOYEE_CREATED_MESSAGE = "Employee Created";


    public static final String RESPONSE_EMPLOYEE_ID_PATH = "Employee.employee_id";
    //rest end points you can add

    public static final String HEADER_CONTENT_TYPE_KEY = "Content-Type";
    public static final String HEADER_CONTENT_TYPE_VALUE = "application/json";
    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
    //Irina
    public static final String DELETE_USER=baseURI+"/deleteEmployee.php";


}
