package com.tokopedia.devcamp.sample.gql;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tokopedia.devcamp.objects.display.pojo.country.CountryDetailArray;
import com.tokopedia.devcamp.objects.display.pojo.country.CountryDetailObject;
import com.tokopedia.devcamp.services.CountriesService;
import com.tokopedia.devcamp.utilities.HelperLogger;

import framework.baseClasses.BaseTest;

/*
*  GraphQL API
*  https://countries.trevorblades.com/
*/
public class TestCountry extends BaseTest{
    
    private CountriesService country;
    private HelperLogger logger;
    private CountryDetailObject country_response_object;
    private CountryDetailArray country_response_array;
    
    @BeforeTest
	public void setUpTestData() {
        // Perform setup operations here before running the tests
        country = new CountriesService();
        logger = new HelperLogger();
        country_response_object = new CountryDetailObject();
        country_response_array = new CountryDetailArray();
    }

    @Test(dataProvider = "ValidCountryGraphQLQuery")
    public void testValidCountryCodeGraphQLQuery(String country_id, String expected_country) {

        country_response_object = country.getCountryByCode(country_id);
        logger.SendLog(country_response_object + "-" + country_id + "-" + expected_country);
        validateCountryDetails(country_response_object, country_id, "", expected_country);
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

        country_response_array = country.getCountryByCurrency(currency);
        logger.SendLog(country_response_array + "-" + currency + "-" + expected_country);
        validateCountryDetails2(country_response_array, "", currency, expected_country);
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

    private void validateCountryDetails(CountryDetailObject country, String country_id, String country_cur, String expected_name) {
        
        String country_capital = country_response_object.getData().getCountry().getCapital();
        String country_name = country_response_object.getData().getCountry().getName();
        String country_currency = country_response_object.getData().getCountry().getCurrency();
        String country_code = country_response_object.getData().getCountry().getCode();

        Assert.assertEquals(country_name, expected_name);
        Assert.assertNotNull(country_capital, "Capital is empty");
        Assert.assertNotNull(country_currency, "Currency is empty");
        Assert.assertNotNull(country_code, "Code is empty");
    }

    private void validateCountryDetails2(CountryDetailArray country, String country_id, String country_cur, String expected_name) {
        
        String country_capital = country_response_array.getData().getCountries().get(0).getCapital();
        String country_name = country_response_array.getData().getCountries().get(0).getName();
        String country_currency = country_response_array.getData().getCountries().get(0).getCurrency();
        String country_code = country_response_array.getData().getCountries().get(0).getCode();

        Assert.assertEquals(country_name, expected_name);
        Assert.assertNotNull(country_capital, "Capital is empty");
        Assert.assertNotNull(country_currency, "Currency is empty");
        Assert.assertNotNull(country_code, "Code is empty");
    }

    @Override
    public void cleanUpTestData() {
        // TODO Auto-generated method stub
    }

    @Override
    public void invokeCleanUpTestData() {
        // TODO Auto-generated method stub
    }

}
