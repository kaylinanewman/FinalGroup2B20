Feature: Adding Dependents

  Background:
    Given user is able to access HRMS application
    When user enters valid Admin Username and Admin Password
    And user clicks on Login button
    Then user is navigated to Dashboard page
    And user clicks on PIM option
    When user clicks on Add Employee option
    When user creates a new employee by sending "Daniela" and "M" and "Smith"
    And retrieves unique EmployeeID
    And clicks on Save button
    And user clicks on Employee List
    When user enters employeeID number
    And user clicks on Search button
    When user clicks on desired id number
    Then user is navigated to Personal Details page
    Then user clicks on Dependents button


  @addDependents
  Scenario Outline:Adding Dependents
    And user clicks on Add button
    Then user verifies that Name, Relationship and Date of Birth fields are displayed and editable
    Then user enters "<Name>"
    Then user selects "<relationship>" from dropdown menu
    And user selects date of birth "<year>" and "<month>" and "<day>"
    And user clicks on Save button
    And dependent is successfully saved
    And the HRMS application should clearly display the list of dependents added by the employee
    #And changes are verified through database using "<Name>"

    Examples:
      | Name         | relationship | year | month | day |
      | Maria Taylor | Child        | 2018 | Jul   | 11  |


  @noName
  Scenario Outline: Adding Dependents with no name
    And user clicks on Add button
    Then user verifies that Name, Relationship and Date of Birth fields are displayed and editable
    Then user enters "<Name>"
    Then user selects "<relationship>" from dropdown menu
    And user selects date of birth "<year>" and "<month>" and "<day>"
    And user clicks on Save button
    And user can see "<Error Message>" error message
    Examples:
      | Name | relationship | year | month | day | Error Message |
      |      | Child        | 2018 | Jul   | 11  | Required      |


  @noRelationship
  Scenario Outline: Adding Dependents with incomplete or invalid dependent information
    And user clicks on Add button
    Then user verifies that Name, Relationship and Date of Birth fields are displayed and editable
    Then user enters "<Name>"
    And user clicks on Save button
    And user can see "<Error Message>" message
    Examples:
      | Name          | Error Message |
      | Silvia Taylor | Required      |


  @editingDependent
  Scenario Outline:Editing a Dependent
    And user clicks on Add button
    Then user verifies that Name, Relationship and Date of Birth fields are displayed and editable
    Then user enters "<Name>"
    Then user selects "<relationship>" from dropdown menu
    And user selects date of birth "<year>" and "<month>" and "<day>"
    And user clicks on Save button
    And dependent is successfully saved
    And the HRMS application should clearly display the list of dependents added by the employee
    And user selects the "<Full Name>" of the dependent to be edited
    Then user selects "<relationship to be changed>" from dropdown menu to edit
    And user "<Specifies Relationship>"
    And user saves the changes successfully
    Examples:
      | Name         | relationship | year | month | day | Full Name    | relationship to be changed | Specifies Relationship |
      | Maria Taylor | Child        | 2018 | Jul   | 11  | Maria Taylor | other                      | Mother                 |


  @removeDependent
  Scenario Outline:Deleting a Dependent
    And user clicks on Add button
    Then user verifies that Name, Relationship and Date of Birth fields are displayed and editable
    Then user enters "<Name>"
    Then user selects "<relationship>" from dropdown menu
    And user clicks on Save button
    And dependent is successfully saved
    And the HRMS application should clearly display the list of dependents added by the employee
    And selects the "<Name>" of the dependent to be deleted
    And deletes the dependent from list
    Then dependent is successfully deleted
    Examples:
      | Name          | relationship |
      | Silvia Taylor | Child        |