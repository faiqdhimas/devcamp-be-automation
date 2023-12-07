package com.tokopedia.devcamp.sample.api;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tokopedia.devcamp.objects.display.pojo.university.UniversityDetail;
import com.tokopedia.devcamp.services.UniversityService;
import com.tokopedia.devcamp.utilities.HelperLogger;

import framework.baseClasses.BaseTest;
import framework.listeners.JiraID;

/*
*  curl --location --request GET
*  http://universities.hipolabs.com/search?name=institut&country=indonesia
*/
public class TestUniversity extends BaseTest{
    
    private String name, country, domain;
    private UniversityDetail[] univ_resp;
    private UniversityService univ_service;
    private HelperLogger logger;

    @BeforeTest
	public void setUpTestData() {
        // Perform setup operations here before running the tests
        univ_service = new UniversityService();
        logger = new HelperLogger();
    }

    @Test(priority = 1)
    @JiraID(id="BTCM-12345")
    public void testInstitutIndonesiaUniv() {
        name = "Institut";
        country = "Indonesia";
        // univ_resp = univ_service.search(name, country);
        univ_resp = univ_service.searchHttp(name, country);
        validateUniversityDetails(univ_resp, name, country);
    }

    @Test(priority = 2)
    public void testInstitutUniv() {
        name = "Institut";
        univ_resp = univ_service.searchName(name);

        validateUniversityDetails(univ_resp, name, "");
    }

    @Test(priority = 3)
    public void testIndonesiaUniv() {
        country = "Indonesia";
        univ_resp = univ_service.searchCountry(country);

        validateUniversityDetails(univ_resp, "", country);
    }

    @Test(priority = 4, dataProvider = "invalidCasesUniversity")
    public void testInvalidUniv(String name, String country) { // Negative case
        univ_resp = univ_service.search(name, country);
        
        Assert.assertTrue(univ_resp.length == 0, "Array is not empty for "+ name + " " + country);
    }

    @DataProvider(name = "invalidCasesUniversity")
    public Object[][] invalidCasesUniversity(){
        return new Object[][]{
                {"Inst!tut", "Indonesia"},
                {"Inst!tut", "XYZ"},
                {"NonExistentUni", "Indonesia"},
                {"123", "Indonesia"},
                {"University", "NonExistentCountry"}
        };
    }

    private void validateUniversityDetails(UniversityDetail[] universities, String filterName, String filterCountry) {
        
        for (int i = 0; i < universities.length; i++) {
            name = universities[i].getName();
            country = universities[i].getCountry();
            domain = universities[i].getDomains().get(0);
            System.out.println(i + ". Validate: " + name + " - " + country + " - " + domain);

            // log for case 1 (search by name & country)
            if ( filterName.length() > 0 && filterCountry.length() > 0 ){
                logger.SendLog(i + ". Univ Detail: " + name + ";" + domain);
            }

            // log for case 2 (search by name)
            if ( filterCountry.isEmpty() && filterName.length() > 0 ){
                logger.SendLog(i + ". Univ Detail: " +name+ " - "+ country);
            }

            // log for case 3 (search by country)
            if ( filterName.isEmpty() && filterCountry.length() > 0 ){
                if (name.contains("Negeri")) {
                    logger.SendLog(i + ". PTN Detail: " + name + ";" + domain);
                }
            }
    
            // Assertion: Verify that the country and name are correct and not empty
            Assert.assertEquals(country, "Indonesia", "Country does not match");
            Assert.assertNotNull(name, "Name is empty");
            Assert.assertNotNull(domain, "Domain is empty");
        }
    
        logger.SendLog("All good");
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
