@CorporateNews
Feature: Get Corporate News on Consumption portal

  @Smoke @TestID01
  Scenario: Get Corporate News on Consumption portal
    Given I have endpoint as "consumptionPortalBaseUrl/GET_CORPORATE_NEWS"
    When I send the "get" request to "get corporate news"
    Then I should see the response status code as "200"
 #   And User verify the API response against the json response schema "GetCorporateNewsResponseSchema"

  @Smoke @TestID02
  Scenario: Create Corporate News on Consumption portal2
    Given I have endpoint as "consumptionPortalBaseUrl/GET_CORPORATE_NEWS"
    When I send the "post" request to "create new article" using request body as "createArticle"
    Then I should see the response status code as "200"
    And Get the ID from the response

  @Smoke @TestID3
  Scenario: Get highlight corporate news on consumption portal
    Given I have endpoint as "consumptionPortalBaseUrl/GET_CORPORATE_NEWS_HIGHLIGHT"
    When I send the "get" request to "get highlight corporate news"
    Then I should see the response status code as "201"

  @Smoke @TestID4
  Scenario: Get top story corporate news on consumption portal
    Given I have endpoint as "consumptionPortalBaseUrl/GET_CORPORATE_NEWS_TOP_STORY"
    When I send the "get" request to "get top story corporate news"
    Then I should see the response status code as "200"