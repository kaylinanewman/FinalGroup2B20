Feature: API workflow

  Background:
    Given a JWT bearer token is generated

  @token
  Scenario: creating a token
    Given a JWT bearer token is generated using "berry@gmail.com" and "berry12345"

  @userCreation
  Scenario Outline: creating username
    Given a request is prepared for creating a user with "<name>" and "<email>" and "<password>"
    When a POST call is made to create the user
    Then the "<status code>" for this request is verified
    Then the respond "<User Created>" for creating a user is stored and values are validated
    Examples:
      | name | email           | password  | status code | User Created |
      | kiwi | kiwi5@gmail.com | kiwi12345 | 201         | User Created |
        #add 1 to kiwi email for the code to work because the email was already used

  @api
  Scenario: create employee
    Given a request is prepared for creating the employee
    When a POST call is made to create the employee
    Then the status code for this request is 201
    Then the respond "Message" is stored and values are validated
    Then the employee id "Employee.employee_id" is stored and values are validated

  @invalidBDayFormat
  Scenario: invalid birthday field format
    Given a request is prepared for creating the employee with incorrect birthday field format
    When a POST call is made to create the employee
    Then the status code for this request is 400
    Then the message "Error" is stored and values are validated

  @invalidGenderField
  Scenario: invalid gender field
    Given a request is prepared for creating the employee with invalid gender field
    When a POST call is made to create the employee
    Then the status code for this request is 400
    Then the error "Message" for invalid gender is stored and values are validated


  @emptyField
  Scenario: empty field
    Given a request is prepared for creating the employee with empty field
    When a POST call is made to create the employee
    Then the status code for this request is 400
    Then the "Error" "Message" is stored and values are validated


  @dynamic
  Scenario: empty field
    Given a request is prepared for creating the employee using "Maria", "", "ms","F", "2000-01-01","permanent","Tester"
    When a POST call is made to create the employee
    Then the status code for this request is 400
    Then the "Error" "Message" is stored and values are validated


