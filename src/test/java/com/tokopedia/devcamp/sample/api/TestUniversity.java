package com.tokopedia.devcamp.sample.api;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tokopedia.devcamp.objects.display.pojo.university.UniversityDetail;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestUniversity {
    
    // curl --location --request GET 'http://universities.hipolabs.com/search?country=Indonesia'

    private String api_path;
    private String name, country, domain;
    private HashMap<String, Object> headerMap = new HashMap<>();
    private UniversityDetail[] univ_resp;

    @BeforeTest
	public void setUpTestData() {
        // Perform setup operations here before running the tests
        RestAssured.baseURI = "http://universities.hipolabs.com";
        api_path = "/search";
    }

    public TestUniversity(){
        this.headerMap.put("Content-Type", "application/json");
    }

    @Test(priority = 1)
    public void testUniversityIndonesia() throws JsonMappingException, JsonProcessingException{
        country = "Indonesia";

        // Perform the GET request
        Response response = RestAssured.given()
                .queryParam("country", country)
                .header("Content-Type", "application/json")
                .when()
                .get(api_path);
                
        System.out.println("Response REST: "+ response.getBody().asString());

        univ_resp = response.as(UniversityDetail[].class);

        // Assertion: Verify that the Country and Name are correct and not null.
        for(int i = 0; i < univ_resp.length; i++) {

            name = univ_resp[i].getName();
            country = univ_resp[i].getCountry();
            domain = univ_resp[i].getDomains().get(0);
            System.out.println(i + ". xValidate: "+name+ " - "+ country+ " - "+ domain);

            Assert.assertEquals(country, "Indonesia", "Country not match");
            Assert.assertNotNull(name, "name is empty");
            Assert.assertNotNull(domain, "domain is empty");
        }
    }
}
