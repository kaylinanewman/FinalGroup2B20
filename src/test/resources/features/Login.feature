Feature: Login scenarios

  @logins
  Scenario: Login with valid username and password
    Given user is able to access HRMS application
    When user enters valid Admin Username and Admin Password
    And user clicks on Login button
    Then user is navigated to dashboard page
    And user clicks on PIM option
    And user clicks on Employee List









