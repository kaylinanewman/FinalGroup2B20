Feature: Authentication Token Generation

  @API_Authentication
  # Scenario for Valid Credentials
  Scenario Outline: Authentication token generation with valid credentials
    Given I have the following credentials:
      | Email   | Password   |
      | <Email> | <Password> |
    When I request an authentication token
    Then I should receive a JWT token in the response <Response>

    Examples:
      | Email                | Password | Response                                    |
      | Moqaddas76@gmail.com | 984mnf   | that contains the user's email and password |

  # Scenario for Invalid Credentials
  Scenario Outline: Authentication token generation with invalid credentials
    Given I have the following credentials:
      | Email   | Password   |
      | <Email> | <Password> |
    When I request an authentication token
    Then I should receive an error message <Message>

    Examples:
      | Email                | Password | Message                        |
      | invalid-email        | 984mnf   | Email or Password is incorrect |
      | Moqaddas76@gmail.com |          | Please fill all inputs         |
