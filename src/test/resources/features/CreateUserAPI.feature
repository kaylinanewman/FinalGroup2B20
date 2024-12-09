Feature: User Creation API

  Background:
    Given a JWT bearer token is generated

  @user
  Scenario Outline: creating user
    Given a request is prepared for creating a user with "<name>" and "<email>" and "<password>"
    When a POST call is made to create the user
    Then the status code for this request is 201
    Then the respond "<User Created>" for creating a user is stored and values are validated
    Examples:
      | name | email | password | User Created |
      | kiwi   |kiwi5@gmail.com| kiwi12345| User Created |


  @existingEmail
  Scenario: creating user with existing email
    Given a request is prepared for creating the user using "kiwi", "kiwi@gmail.com" and "kiwi12345"
    When a POST call is made to create the user with invalid email
    Then the status code for this invalid email request is 200
    Then the "Message" "The email address you have entered is already registered" for this request is stored and values are validated

  @invalidEmail
  Scenario: creating user with invalid email
    Given a request is prepared for creating the user using "kiwi", "kiwigmail.com" and "kiwi12345"
    When a POST call is made to create the user with invalid email
    Then the status code for this invalid email request is 400
    Then the "condition" "error" and "data" "Invalid Email" is stored and values are validated

  @missingPassword
  Scenario: creating user with missing password
    Given a request is prepared for creating the user using "kiwi", "kiwi@gmail.com" and ""
    When a POST call is made to create the user with missing data
    Then the status code for this invalid data request is 400
    Then the "condition" "error" and "data" "Please fill all inputs" is stored and values are validated

  @missingNameField
  Scenario: creating user with missing name field
    Given a request is prepared for creating the user using "", "kiwi@gmail.com" and "kiwi12345"
    When a POST call is made to create the user with missing data
    Then the status code for this invalid data request is 400
    Then the "condition" "error" and "data" "Please fill all inputs" is stored and values are validated
