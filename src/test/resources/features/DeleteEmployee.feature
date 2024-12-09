Feature: Delete Employee API

  As a tester, I want to validate the API for deleting an existing employee record using the employee ID,
  so that the employee's information is removed from the system and appropriate error messages are displayed
  when accessing deleted records.
  Background:
    Given generate JWT bearer token
  @scenario1
  Scenario: Successfully delete an existing employee with provided Employee ID
    Given a request is prepared for creating the employee with dynamic json payload usgin data "irina" , "grusco" , "ms" , "F" , "1993-11-01" , "intern" , "CEO"
    When a POST call request is made to create the employee
    Then the resulting status code for this request is 201
    And the employee id "Employee.employee_id" is stored and values are validated
    When a request is prepared for deleting the employee
    And a delete call is made to remove the employee
    Then the status code for the delete request is 200
    And the response message for deletion is "Employee deleted" and includes deleted employee details

  @scenario2
  Scenario: Attempt to delete an employee with non-existent/previously deleted employee ID
    Given a request is prepared for deleting employee with non-existent employee ID
    When a DELETE call is made to remove the employee with non-existent employee ID
    Then the error status code for the delete request is 404
    And the error message for non-existent employee is "Employee does not exist or you have provided invalid employee_id"
  @scenario3
  Scenario: Attempt to delete an employee without providing an employee ID
    Given a request is prepared without employee ID parameter for deletion
    When a DELETE call is made to remove the employee without employee ID
    Then the status code for the delete request is 400
    And the error message for missing employee ID is "Please provide employee_id"
