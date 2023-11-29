package com.tokopedia.devcamp.services;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import com.tokopedia.devcamp.objects.display.pojo.university.UniversityDetail;

/*
*  GraphQL API
*  https://countries.trevorblades.com/
*/
public class CountriesService {

    // Endpoint dari Star Wars GraphQL API
    private static final String BASE_URL = "https://countries.trevorblades.com";
 
    public CountriesService() {
      RestAssured.baseURI = BASE_URL;
    }

   public Response getCountryByCode(String countryCode) {
      
      String variableName = "countryCode";
      String query = "query GetCountryByCode($countryCode: ID!) { "
      + "country(code: $countryCode) { "
      + "name code capital currency languages { name }"
      + "} }";

      return this.executeGraphQLQuery(query, variableName, countryCode);
    }

    public Response getCountryByCurrency(String currency) {

      String variableName = "currency";
      String query = "query GetCountryByCurrency($currency: String!) { "
      + "countries(filter: {currency: {eq: $currency}}) { "
      + "name code capital currency languages { name }"
      + "} }";

      return this.executeGraphQLQuery(query, variableName, currency);
    }

    public static Response executeGraphQLQuery(String query, String variableName, String variableValue) {

      return RestAssured.given()
              .contentType("application/json")
              .body("{\"query\": \"" + query + "\", \"variables\": {\"" + variableName + "\": \"" + variableValue + "\"}}")
              .when()
              .post();
    }

}
