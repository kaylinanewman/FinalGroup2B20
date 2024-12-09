Feature: Employee Search Functionality

  Background:
    #Given user is able to access HRMS application
    When user enters admin username and admin password
    And user clicks on login button
    Then user is navigated to dashboard page
    When user clicks on PIM option
    And user clicks on employee list option

  @namesearch
  Scenario: Search for an employee by full name (first name and last name)
    And user enters employee name
    When user clicks on search button
    Then user is able to see all matching employee records for searched name

  @partialname
  Scenario: Search for an employee by partial name
    And user enters employee partial name
    When user clicks on search button
    Then user is able to see all matching employee records for searched partial name

  @caseinsensitive
  Scenario: Search for an employee by case insensitive name
    And user enters employee name all lowercase
    When user clicks on search button
    Then user is able to see all matching employee records for searched name

  @employeeID
  Scenario: Search for an employee by unique employee ID
    And user enters employee ID
    When user clicks on search button
    Then user is able to see on the screen searched  employee by ID

  @invalidname
  Scenario: No results for invalid search criteria
    And user enters invalid employee name
    When user clicks on search button
    Then user should see the message "No Records Found"

