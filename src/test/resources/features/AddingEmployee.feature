Feature: Adding an Employee to the HRMS Application.

  Background:
    Given user is able to access HRMS application
    When user enters valid Admin Username and Admin Password
    And user clicks on Login button
    Then user is navigated to dashboard page
    And user clicks on PIM option
    When user clicks on Add Employee option

  @smoke @regression
  Scenario: Admin adds a new employee without an employee ID
    When user creates a new employee by sending "June" and "" and "Smith"
    When user clicks on save button
    And the system should generate a unique employee ID for the new employee
    Then the employee should be added to the system with the provided details

  @test @regression
  Scenario: Admin adds a new employee with a provided employee ID
    When user creates a new employee by sending "Karl" and "" and "Smith"
    And user enters an employeeID
    When user clicks on save button
    And the employee should be added to the system with name "Karl" and "" and "Smith"

  @b20 @regression  @run
  Scenario: System displays error messages for incomplete employee information
    When user creates a new employee by sending "" and "" and "Smith"
    When user clicks on save button
    Then the system should display a last name error message "Required"

  @b20 @regression
  Scenario: Missing first name
    When user creates a new employee by sending "Karl" and "" and ""
    When user clicks on save button
    Then the system should display an error message "Required"
