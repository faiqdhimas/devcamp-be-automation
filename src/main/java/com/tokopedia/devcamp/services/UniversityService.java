package com.tokopedia.devcamp.services;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import com.tokopedia.devcamp.objects.display.pojo.university.UniversityDetail;

import framework.utilities.http.HttpClient;

/*
*  curl --location --request GET
*  http://universities.hipolabs.com/search?name=institut&country=indonesia
*/
public class UniversityService {

    String host = "http://universities.hipolabs.com";
    String apiPathSearch = "/search";
    HashMap<String, Object> headerMap = new HashMap<>();
    Map<String, String> headerMaps = new HashMap<>();
    HttpClient client = new HttpClient();
    Response response;    

    public UniversityService() {
      RestAssured.baseURI = host;
      this.headerMap.put("Content-Type", "application/json");
    }

   public UniversityDetail[] searchName(String name) {
      return this.search(name, "");
   }

   public UniversityDetail[] searchCountry(String country) {
      return this.search("", country);
   }

   // Example using Rest Assured
   public UniversityDetail[] search(String name, String country) {

      // Perform the GET request
      response = RestAssured.given()
         .queryParam("name", name)
         .queryParam("country", country)
         .header("Content-Type", "application/json")
         .when()
         .get(apiPathSearch);

      response.then().statusCode(200);

      return response.as(UniversityDetail[].class);
    }

    // Example using HTTP Client
    public UniversityDetail[] searchHttp(String name, String country){

      // Perform the GET request
      headerMaps.put("Content-Type", "application/json");
      apiPathSearch += "?name=" + name + "&country=" + country;
      UniversityDetail[] univdetailresp = client.get(new HashMap(), new HashMap(), headerMaps, apiPathSearch, host, UniversityDetail[].class);

      return univdetailresp;
    }
    
}
