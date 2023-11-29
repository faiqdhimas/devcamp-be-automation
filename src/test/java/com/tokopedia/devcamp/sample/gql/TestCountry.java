package com.tokopedia.devcamp.sample.gql;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tokopedia.devcamp.objects.display.pojo.university.UniversityDetail;
import com.tokopedia.devcamp.services.CountriesService;
import com.tokopedia.devcamp.utilities.HelperLogger;

import framework.baseClasses.BaseTest;
import io.restassured.response.Response;

/*
*  GraphQL API
*  https://countries.trevorblades.com/
*/
public class TestCountry extends BaseTest{
    
    private String name, domain;
    private UniversityDetail[] univ_resp;

    //pojostarwars belummm
    private CountriesService country;
    private HelperLogger logger;
    
    @BeforeTest
	public void setUpTestData() {
        // Perform setup operations here before running the tests
        country = new CountriesService();
        logger = new HelperLogger();
    }

    // @Test(dataProvider = "ValidCountryGraphQLQuery")
    public void testValidCountryCodeGraphQLQuery(String country_id, String expected_country) {

        Response response = country.getCountryByCode(country_id);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Failed to execute GraphQL query. Status code: " + statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Country Response Bodyyy: " + responseBody);

        // Validate hasil query sesuai dengan respons yang diharapkan
        Assert.assertTrue(responseBody.contains("country"), "Response does not contain expected data");
        Assert.assertFalse(responseBody.contains("errors"), "Response contains errors");
        Assert.assertTrue(responseBody.contains("name"), "Response does not contain country name");
        Assert.assertTrue(responseBody.contains("code"), "Response does not contain country code");
        Assert.assertTrue(responseBody.contains("capital"), "Response does not contain country capital");
        Assert.assertTrue(responseBody.contains("currency"), "Response does not contain country currency");
        Assert.assertTrue(responseBody.contains("languages"), "Response does not contain country languages");
    }

    @DataProvider(name = "ValidCountryGraphQLQuery")
    public Object[][] ValidCountryGraphQLQuery(){
        return new Object[][]{
                {"ID", "Indonesia"},
                {"RU", "Russia"},
                {"MY", "Malaysia"},
                {"AR", "Argentina"},
                {"SG", "Singapore"}
        };
    }

    @Test(dataProvider = "ValidCountryCurGraphQLQuery")
    public void testValidCountryCurrencyGraphQLQuery(String currency, String expected_country) {

        Response response = country.getCountryByCurrency(currency);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Failed to execute GraphQL query. Status code: " + statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Currency Response Bodyyy: " + responseBody);

        // Validate hasil query sesuai dengan respons yang diharapkan
        // Assert.assertTrue(responseBody.contains("country"), "Response does not contain expected data");
        Assert.assertFalse(responseBody.contains("errors"), "Response contains errors");
        Assert.assertTrue(responseBody.contains("name"), "Response does not contain country name");
        Assert.assertTrue(responseBody.contains("code"), "Response does not contain country code");
        Assert.assertTrue(responseBody.contains("capital"), "Response does not contain country capital");
        Assert.assertTrue(responseBody.contains("currency"), "Response does not contain country currency");
        Assert.assertTrue(responseBody.contains("languages"), "Response does not contain country languages");
    }

    @DataProvider(name = "ValidCountryCurGraphQLQuery")
    public Object[][] ValidCountryCurGraphQLQuery(){
        return new Object[][]{
                {"ARS", "Argentina"},
                {"IDR", "Indonesia"},
                {"RUB", "Russia"},
                {"SGD", "Singapore"}
        };
    }

    @Override
    public void cleanUpTestData() {
        // TODO Auto-generated method stub
    }

}
