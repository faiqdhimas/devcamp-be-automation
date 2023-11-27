package com.tokopedia.devcamp.services;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import com.tokopedia.devcamp.objects.display.pojo.university.UniversityDetail;

/*
*  curl --location --request GET
*  http://universities.hipolabs.com/search?name=institut&country=indonesia
*/
public class UniversityService {

    String host = "http://universities.hipolabs.com";
    String apiPathSearch = "/search";
    HashMap<String, Object> headerMap = new HashMap<>();
 
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

   public UniversityDetail[] search(String name, String country) {

      // Perform the GET request
      Response response = RestAssured.given()
         .queryParam("name", name)
         .queryParam("country", country)
         .header("Content-Type", "application/json")
         .when()
         .get(apiPathSearch);

      response.then().statusCode(200);

      return response.as(UniversityDetail[].class);
    }

}
