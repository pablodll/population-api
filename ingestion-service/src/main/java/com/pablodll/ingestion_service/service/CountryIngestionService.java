package com.pablodll.ingestion_service.service;

import com.pablodll.ingestion_service.dto.CountryRequestDTO;
import com.pablodll.ingestion_service.dto.RestCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CountryIngestionService {

    private final RestTemplate restTemplate;
    private final String targetApiUrl;
    private final String sourceUrl;

    @Autowired
    public CountryIngestionService(RestTemplate restTemplate,
                                   @Value("${app.api.target-url}") String targetApiUrl,
                                   @Value("${app.api.source-url}") String sourceUrl) {
        this.restTemplate = restTemplate;
        this.targetApiUrl = targetApiUrl; // Example: http://localhost:8080/api/v1/data/country
        this.sourceUrl = sourceUrl; // Example: https://restcountries.com/v3.1/all?fields=name,population,cca3
    }

    private List<CountryRequestDTO> getCountries(){
        RestCountryResponse[] response = restTemplate.getForObject(
                sourceUrl,
                RestCountryResponse[].class
        );

        if (response == null) return null;

        return Arrays.stream(response)
                .map(c -> new CountryRequestDTO(
                        c.cca3(),
                        c.name().common(),
                        c.population()))
                .toList();
    }

    /**
     * Ingest country data from restcountries.com external API to DB through country-api-service POST Method.
     */
    public void ingestCountriesHTTP() {
        List<CountryRequestDTO> countryList = getCountries();

        if (countryList == null) return;

        for (CountryRequestDTO dto : countryList) {
            try {
                restTemplate.postForObject(targetApiUrl, dto, String.class);
            } catch (Exception e) {
                System.err.println("Error posting country: " + dto.code() + " -> " + e.getMessage());
            }
        }
    }

    /**
     * Ingest countries directly to the DB
     */
    public void ingestCountriesDB(){
        List<CountryRequestDTO> countryList = getCountries();

        if (countryList == null) return;

        for (CountryRequestDTO dto : countryList) {
            // DB Writing
        }
    }
}
