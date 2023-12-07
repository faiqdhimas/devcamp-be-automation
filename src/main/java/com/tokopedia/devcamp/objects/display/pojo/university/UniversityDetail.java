package com.tokopedia.devcamp.objects.display.pojo.university;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@Getter @Setter @NoArgsConstructor @JsonIgnoreProperties(ignoreUnknown = true)
public class UniversityDetail {

    @JsonProperty(value="name")
    private String name;

    @JsonProperty(value="country")
    private String country;

    @JsonProperty(value="state-province")
    private String state_province;

    @JsonProperty(value="alpha_two_code")
    private String alpha_two_code;

    @JsonProperty(value="domains")
    private ArrayList<String> domains;

    @JsonProperty(value="web_pages")
    private ArrayList<String> web_pages;
}
