Feature: Manage employee job details

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
    When user clicks on desired id number

  @updated
  Scenario Outline: Admin adds/updates current job details
    Then the admin user clicks on the Job tab
    When the admin user clicks the Edit button
    Then the admin user should be able to edit the following fields:
      | Field             | Value               |
      | Name              | <Name>              |
      | Job Title         | <Job Title>         |
      | Employment Status | <Employment Status> |
      | Joined Date       | <Joined Date>       |
      | Sub Unit          | <Sub Unit>          |
      | Location          | <Location>          |
      | Start Date        | <Start Date>        |
      | End Date          | <End Date>          |
    And the admin user clicks the Save button


    Examples:
      | Name       | Job Title         | Employment Status | Joined Date | Sub Unit   | Location | Start Date | End Date   |
      | Adam Aspen | Software Engineer | FullTime          | 2020-11-07  | Account    | Chicago  | 2020-01-01 | 2024-01-05 |
      | Will ms    | Hashira           | here              | 2020-05-15  | IT Support | HQ       | 2020-01-05 | 2020-07-30 |