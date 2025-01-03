package utils;

public class APIPayload {
    private String email;
    private String password;

    public APIPayload(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "APIPayload{" +
                APIConstants.EMAIL_PARAM + "='" + email + '\'' +
                ", " + APIConstants.PASSWORD_PARAM + "='" + password + '\'' +
                '}';
    }
}
