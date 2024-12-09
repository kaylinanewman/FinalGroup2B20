package utils;

import org.json.JSONObject;

public class CreateUserApiPayloadConstants {

    public static String userCreation
            (String name, String email, String password) {
        if (email.equalsIgnoreCase("kiwi5@gmail.com")) {
            email = "kiwi" + System.currentTimeMillis() + "@gmail.com";
        }
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("email", email);
        obj.put("password", password);
        return obj.toString();
    }

    public static String createUserJsonDynamic
            (String name,
             String email,
             String password) {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("email", email);
        obj.put("password", password);

        return obj.toString();
    }

}

