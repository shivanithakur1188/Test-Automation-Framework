package com.taf.api.endpoints;

public class APIEndPoints {
    public static final String PETS_BY_STATUS = "/v2/pet/findByStatus";
    public static final String PET = "/v2/pet";
    public static final String PET_BY_ID = "/v2/pet/{petId}";

    //Consumption portal endpoints
    public static final String GET_CORPORATE_NEWS = "/api/corporate-news";
    public static final String GET_CORPORATE_NEWS_HIGHLIGHT = "/api/corporate-news?publicationState=live&populate=*&locale=en&sort=publishedAt:desc&filters[Location][$eq]=Highlight&pagination[limit]=1";
    public static final String GET_CORPORATE_NEWS_TOP_STORY = "/api/corporate-news?publicationState=live&populate=*&locale=en&sort=publishedAt:desc&filters[Location][$eq]=Top Story&pagination[limit]=1";
    
}
