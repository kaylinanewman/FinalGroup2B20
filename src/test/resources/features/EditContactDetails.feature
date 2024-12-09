Feature: Employee updates his contact information


  Background:
#Given user is able to access HRMS application
    When user enters admin username and admin password
    And user clicks on login button
    Then user is navigated to dashboard page
    When user clicks on PIM option
    And user clicks on employee list option
    And user enters employee ID
    And user clicks on search button
    Then user is able to see searched employee on screen

  @updated
  Scenario Outline: Edit contact information successfully
    When user clicks on id field
    Then user clicks the Contact Details section
    And user clicks on edit button
    And user updates his contact information with the following details: "<addressStreet1>" and "<addressStreet2>" and "<city>" and "<state>" and "<zipCode>" and "<country>" and "<homePhone>" and "<mobilePhone>" and "<workPhone>" and "<workEmail>" and "<otherEmail>"
    Then the contact information should be updated successfully with the following details: "<addressStreet1>" and "<addressStreet2>" and "<city>" and "<state>" and "<zipCode>" and "<country>" and "<homePhone>" and "<mobilePhone>" and "<workPhone>" and "<workEmail>" and "<otherEmail>"
    Examples:
      |addressStreet1|addressStreet2|city    |state      |zipCode|country      |homePhone   |mobilePhone |workPhone   |workEmail     |otherEmail  |
      |2630 Ocean Ave|Apt A4        |Brooklyn|New York   |11229  |United States|123-456-7890|987-654-3210|456-789-0123|nick1@gmail.com|nick2@aol.com|



