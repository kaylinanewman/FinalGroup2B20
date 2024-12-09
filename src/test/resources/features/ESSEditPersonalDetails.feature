Feature: As an ESS user,I want to be able to edit my personal information within the HRMS application
  Background:
    Given I am logged into the HRMS application as an ESS user,
    When I navigate to the profile picture upload page,

  @ESSEditInfo
  Scenario Outline: :
    And user clicks on the edit button in personal details page
    Then user updates "<firstname>", "<middlename>" and "<lastname>" field
    And user updates "<gender>" button
    And user updates "<nationality>" and "<maritalstatus>" field
    Then user clicks on the save button
    And the updated personal information is successfully saved
    Then changes are verified through database
    Examples:
      | firstname | middlename | lastname     | gender   | nationality  | maritalstatus  |
      |Laura      |   M        |   Taylor     | Female   |  Guatemalan  |  Married       |