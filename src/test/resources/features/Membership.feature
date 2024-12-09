Feature: Membership scenarios

  Background:
    When user enters admin username and admin password
    And user clicks on login button
    Then user is navigated to dashboard page

  @Myinfo
  Scenario: Membership
    When the user clicks on the MyInfo option
    When the user clicks on Membership
    When the user clicks on Add to assigned Membership
    When the user selects a  membership
    When the user selects the Subscription Paid By option
    When the user enters the  subscription amount
    When the user selects the  currency for the subscription
    When the user selects the  subscription date
    When the user selects the  renewal date
    Then the user sees a success message Membership saved successfully