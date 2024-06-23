@petStore @api
Feature: Demo Pet Store

  Scenario: Get available pets
    Given I have the endpoint as "PETS_BY_STATUS"
    When I send the "get" request to "check available pets"
      | description | key    | value     |
      | queryParam  | status | available |
    Then I should see the response status code as "200"
    And I should see list of available pets

  Scenario: Post a new available pet to the store
    Given I have the endpoint as "PET"
    When I send the "post" request to "add a new available pet to the store" using request body as "AddNewPet"
    Then I should see the response status code as "200"
    And I should see the following parameters in response
      | Description          | Key          |
      | learningExperienceId | JSON_PATH_ID |
    And I have the endpoint as "PET_BY_ID"
    And I send the "get" request to "check newly added pet"
      | description | key   |    |
      | pathParam   | petId | ID |
    And I should see the response status code as "200"
    And I should see the following parameters in response as
      | parameter  | jsonpath | expectedValue |
      | pet status | status   | available     |

  Scenario: Update status of existing available pet to sold
    Given I have the endpoint as "PET"
    When I send the "put" request to "update status of pet as sold" using request body as "UpdatePet" using
      | description      | key    | value |
      | key to be update | id     | ID  |
      | key to be update | status | sold  |
    Then I should see the response status code as "200"
    And I have the endpoint as "PET_BY_ID"
    And I send the "get" request to "check newly added pet"
      | description | key   |    |
      | pathParam   | petId | ID |
    And I should see the response status code as "200"
    And I should see the following parameters in response as
      | parameter  | jsonpath | expectedValue |
      | pet status | status   | sold          |

  Scenario: Delete pet
    Given I have the endpoint as "PET_BY_ID"
    When I send the "delete" request to "delete newly added pet"
      | description | key   |    |
      | pathParam   | petId | ID |
    Then I should see the response status code as "200"
    And I send the "get" request to "check deleted pet should not exist"
      | description | key   |    |
      | pathParam   | petId | ID |
    And I should see the response status code as "404"
    And I should see the following parameters in response as
      | parameter  | jsonpath | expectedValue |
      | pet status | message  | Pet not found |
     Then I should verify printing of logs 
