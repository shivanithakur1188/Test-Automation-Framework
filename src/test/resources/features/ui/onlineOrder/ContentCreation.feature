@ComsumptionPortal=Test
Feature: Create new entry

  Background:
    Given The user logged in to the application

  @CreateDraft @Test
  Scenario: To validate if the "Create new entry" button is displayed and enabled on "Corporate News" dashboard page
    When The user clicked on content manager
    Then The user should see a Create new entry button

  @CreateDraft @Test
  Scenario: To validate if form after clicking "Create new entry" button has required details
    Given The user clicked on content manager
    When The user should see a Create new entry button
#    Then The user should find all mandatory and non mandatory fields on the form
#      |Article Title           |
#      |Subtitle                |
#      |Category                |
#      |Hero Image              |
#      |Body Content            |
#      |Location                |

#  @CreateDraft @Test
#  Scenario: To validate that user is not able to save the "Create new entry" form when "Category" dropdown is empty
#    Given The user clicked on existing collection
#    When The user clicked Create new entry button
#    And The user clicked Save button after entering Article title
#      |The Woodlands of Hitachi High-Tech Science Certified by the Ministry of the Environment as a Natural Symbiosis Site|
#    Then The user should not be able to save form with blank "Category" field
#
#  @SaveDraft @Test
#  Scenario: To validate if the "Save" button is displayed and disabled on "Create an entry" page after "Create nre entry" button click
#    Given The user clicked on existing collection
#    When The user clicked Create new entry button
#    Then The user should find the Save button disabled
#
#  @SaveDraft @Test
#  Scenario: To validate a confirmation message appears before leaving page with unsaved changes
#    Given The user clicked on existing collection
#    And The user clicked Create new entry button
#    When The user entered Article title
#      |Contributing to Efficient Development of Metal Thin Film Materials by Using Chemicals Informatics and Materials Informatics|
#    And The user clicked back button to leave page
#    Then The user should get a confirmation popup
#
#  @SaveContent
#  Scenario: To Verify if "Save" button is Working on Create an Entry Page
#    Given The user clicked on existing collection
#    And The user clicked Create new entry button
#    When The user filled data in all fields and saved draft
#    Then The user should be able to Publish the article
#
