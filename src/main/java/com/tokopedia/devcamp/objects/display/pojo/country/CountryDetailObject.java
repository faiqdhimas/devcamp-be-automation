package com.tokopedia.devcamp.objects.display.pojo.country;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDetailObject {

    @JsonProperty(value="data")
    private CountryData data;

    @Data @NoArgsConstructor @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CountryData {
        @JsonProperty(value="country")
        private CountriesDetail country;
    }
    
    @Data @NoArgsConstructor @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CountriesDetail {
        @JsonProperty(value="name")
        private String name;

        @JsonProperty(value="code")
        private String code;

        @JsonProperty(value="capital")
        private String capital;

        @JsonProperty(value="currency")
        private String currency;

        @JsonProperty(value="languages")
        private List<Language> languages;
    }

    @Data @NoArgsConstructor @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Language {
        @JsonProperty(value="name")
        private String name;
    }
}