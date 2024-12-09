package utils;

import org.json.JSONObject;

import java.util.UUID;

public class APIPayloadConstants {
    // Common Payload Fields
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    public static String createUserJsonPayloadDynamic
            (String name,
             String password) {
        String email = UUID.randomUUID().toString().substring(0, 8) + "@example.io";
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("email", email);
        obj.put("password", password);
        return obj.toString();
    }


    public static String createTokenPayload() {
        return """
                {
                  "email": "berry@gmail.com",
                  "password": "berry12345"
                }""";
    }

    public static String createTokenPayloadDynamic
            (String email, String password) {
        JSONObject obj = new JSONObject();
        obj.put("email", email);
        obj.put("password", password);
        return obj.toString();
    }


    public static String createEmployeePayload() {
        return """
                {
                    "emp_firstname": "Maria",
                    "emp_lastname": "Smith",
                    "emp_middle_name": "A",
                    "emp_gender": "F",
                    "emp_birthday": "2000-01-12",
                    "emp_status": "permanent",
                    "emp_job_title": "Tester"
                }
                """;
    }

    public static String createEmployeePayloadIncorrectBDayFormat() {
        return """
                {
                  "emp_firstname": "Maria",
                  "emp_lastname": "Smith",
                  "emp_middle_name": "A",
                  "emp_gender": "F",
                  "emp_birthday": "12/01/2000",
                  "emp_status": "permanent",
                  "emp_job_title": "Tester"
                }""";
    }

    public static String createEmployeePayloadInvalidGenderField() {
        return """
                {
                  "emp_firstname": "Maria",
                  "emp_lastname": "Smith",
                  "emp_middle_name": "A",
                  "emp_gender": "S",
                  "emp_birthday": "2000-01-12",
                  "emp_status": "permanent",
                  "emp_job_title": "Tester"
                }""";
    }


    public static String createEmployeePayloadEmptyField() {
        return """
                {
                  "emp_firstname": "",
                  "emp_lastname": "Smith",
                  "emp_middle_name": "A",
                  "emp_gender": "S",
                  "emp_birthday": "2000-01-12",
                  "emp_status": "permanent",
                  "emp_job_title": "Tester"
                }""";
    }

    public static String createEmployeeJsonPayloadDynamic
            (String emp_firstname,
             String emp_lastname,
             String emp_middle_name,
             String emp_gender,
             String emp_birthday,
             String emp_status,
             String emp_job_title) {
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", emp_firstname);
        obj.put("emp_lastname", emp_lastname);
        obj.put("emp_middle_name", emp_middle_name);
        obj.put("emp_gender", emp_gender);
        obj.put("emp_birthday", emp_birthday);
        obj.put("emp_status", emp_status);
        obj.put("emp_job_title", emp_job_title);
        return obj.toString();
    }

}
