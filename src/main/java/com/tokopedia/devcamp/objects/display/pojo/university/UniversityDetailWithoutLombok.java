package com.tokopedia.devcamp.objects.display.pojo.university;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UniversityDetailWithoutLombok {

    private String name;
    private String country;
    private String state_province;
    private String alpha_two_code;
    private ArrayList<String> domains;
    private ArrayList<String> web_pages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState_province() {
        return state_province;
    }

    public void setState_province(String state_province) {
        this.state_province = state_province;
    }

    public String getAlpha_two_code() {
        return alpha_two_code;
    }

    public void setAlpha_two_code(String alpha_two_code) {
        this.alpha_two_code = alpha_two_code;
    }

    public ArrayList<String> getDomains() {
        return domains;
    }

    public void setDomains(ArrayList<String> domains) {
        this.domains = domains;
    }

    public ArrayList<String> getWeb_pages() {
        return web_pages;
    }

    public void setWeb_pages(ArrayList<String> web_pages) {
        this.web_pages = web_pages;
    }
}
