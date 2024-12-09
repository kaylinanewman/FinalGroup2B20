Feature: Creating Login Details for an Employee in the HRMS Application
  As an HR administrator
  I want to create login details for an employee in the HRMS application
  So that the employee can log in with the provided credentials

  Background:
    Given I am logged into the HRMS application as an administrator
    And I navigate to the "Add Employee" page

  Scenario: Enabling login details creation
    When I check the "Create Login Details" checkbox
    Then the login details fields should be enabled

  Scenario: Providing a unique username
    Given the login details fields are enabled
    When I enter "unique_username" in the "Username" field
    Then the username field should accept the entered value
    And the username should be validated for uniqueness

  Scenario: Entering a valid password
    Given the login details fields are enabled
    When I enter "StrongP@ssw0rd" in the "Password" field
    Then the password field should display a hint message
      """
      For a strong password, please use a hard to guess combination of text with upper and lower case characters, symbols and numbers
      """
    And the password should meet the complexity requirements

  Scenario: Confirming the password
    Given the login details fields are enabled
    And "StrongP@ssw0rd" is entered in the "Password" field
    When I enter "StrongP@ssw0rd" in the "Confirm Password" field
    Then the system should validate that the confirm password matches the password

  Scenario: Setting the login status
    Given the login details fields are enabled
    When I select "Enabled" from the "Status" dropdown
    Then the status should be set to "Enabled"

  Scenario: Successfully submitting the login details
    Given all mandatory fields are filled in correctly
    When I click the "Save" button
    Then the system should save the login details
    And the employee should be able to log in to the HRMS application using the created credentials