Feature: Profile Picture Upload for ESS Users

  Background:
    Given I am logged into the HRMS application as an ESS user,
    When I navigate to the profile picture upload page,
    Then I click profile picture to change the photo,

  @ESSSuccess
  Scenario Outline: Uploading a Profile Picture (Success Cases)

    And I select a profile picture "<Filename>" with a size of <Filesize> bytes,
    And I click on the "Upload" button,
    Then I should see a success message "<Success Message>",
    And the profile picture should be displayed.

    Examples:
      | Filename    | Filesize | Success Message             |
      | profile.jpg | 500000   | Successfully Uploaded Close |
      | profile.png | 800000   | Successfully Uploaded Close |
      | profile.gif | 900000   | Successfully Uploaded Close |

  @ESSFailure
  Scenario Outline: Uploading a Profile Picture (Error Cases)

    And I select a profile picture "<Filename>" with a size of <Filesize> bytes,
    And I click on the "Upload" button,
    But if the upload fails, I should see an error message "<Error Message>".

    Examples:
      | Filename           | Filesize | Error Message                               |
      | large_profile.jpg  | 2000000  | Failed to Save: File Size Exceeded Close    |
      | invalid_format.bmp | 300000   | Failed to Save: File Type Not Allowed Close |
