@ComsumptionPortal @Test
Feature: Create new entry

  @CreateDraft
  Scenario: To validate if the "Create new entry" button is displayed and enabled on "Corporate News" dashboard page
    Given The user logged in to the application
    When The user clicked on content manager
    Then The user should see a Create new entry button

  @CreateDraft
  Scenario: To validate if form after clicking "Create new entry" button has required details
    Then The user should see all mandatory fields on the form
      | Article Title |
      | Subtitle      |
      | Category      |
      | Hero Image    |
      | Body Content  |
      | Location      |
      
  @SaveDraft
  Scenario: To validate if the "Save" button is displayed and disabled on "Create an entry" page after "Create nre entry" button click
    Then The user should find the Save button disabled

  @CreateDraft
  Scenario: To validate that user is not able to save the "Create new entry" form when "Category" dropdown is empty
    And The user clicked Save button after entering Article title
      | The Woodlands of Hitachi High-Tech Science Certified by the Ministry of the Environment as a Natural Symbiosis Site |
    Then The user should not be able to save form with blank "Category" field

  @SaveDraft
  Scenario: To validate a confirmation message appears before leaving page with unsaved changes
    And The user clicked back button to leave page
    Then The user should get a confirmation popup
      | Are you sure you want to leave this page? All your modifications will be lost |

  @SaveContent
  Scenario: To Verify if "Save" button is Working on Create an Entry Page
    When The user filled data in all fields and saved draft
    Then The user should be able to Publish the article
