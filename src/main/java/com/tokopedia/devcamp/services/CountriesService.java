package com.tokopedia.devcamp.services;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import com.tokopedia.devcamp.objects.display.pojo.country.CountryDetailArray;
import com.tokopedia.devcamp.objects.display.pojo.country.CountryDetailObject;

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

   public CountryDetailObject getCountryByCode(String countryCode) {
      
      String variableName = "countryCode";
      String query = "query GetCountryByCode($countryCode: ID!) { "
      + "country(code: $countryCode) { "
      + "name code capital currency languages { name }"
      + "} }";

      return this.executeGraphQLQuery(query, variableName, countryCode);
    }

    public CountryDetailArray getCountryByCurrency(String currency) {

      String variableName = "currency";
      String query = "query GetCountryByCurrency($currency: String!) { "
      + "countries(filter: {currency: {eq: $currency}}) { "
      + "name code capital currency languages { name }"
      + "} }";

      return this.executeGraphQLQuery2(query, variableName, currency);
    }

    public static CountryDetailObject executeGraphQLQuery(String query, String variableName, String variableValue) {

      Response response = RestAssured.given()
              .contentType("application/json")
              .body("{\"query\": \"" + query + "\", \"variables\": {\"" + variableName + "\": \"" + variableValue + "\"}}")
              .when()
              .post();

      response.then().statusCode(200);
      System.out.println("Response Service1: "+ response.body().asString());
      return response.as(CountryDetailObject.class);
    }

    public static CountryDetailArray executeGraphQLQuery2(String query, String variableName, String variableValue) {

      Response response = RestAssured.given()
              .contentType("application/json")
              .body("{\"query\": \"" + query + "\", \"variables\": {\"" + variableName + "\": \"" + variableValue + "\"}}")
              .when()
              .post();

      response.then().statusCode(200);
      System.out.println("Response Service2: "+ response.body().asString());
      return response.as(CountryDetailArray.class);
    }
}
