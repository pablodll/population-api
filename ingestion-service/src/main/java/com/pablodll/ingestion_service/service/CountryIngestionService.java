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

    @Autowired
    public CountryIngestionService(RestTemplate restTemplate, @Value("${app.api.target-url}") String targetApiUrl) {
        this.restTemplate = restTemplate;
        this.targetApiUrl = targetApiUrl; // Example: http://localhost:8080/api/v1/data/country
    }

    public void ingestCountries() {
        String externalUrl = "https://restcountries.com/v3.1/all?fields=name,population,cca3";

        RestCountryResponse[] response = restTemplate.getForObject(
                externalUrl,
                RestCountryResponse[].class
        );

        if (response == null) return;

        List<CountryRequestDTO> countryList = Arrays.stream(response)
                .map(c -> new CountryRequestDTO(
                        c.cca3(),
                        c.name().common(),
                        c.population()))
                .toList();

        for (CountryRequestDTO dto : countryList) {
            try {
                restTemplate.postForObject(targetApiUrl, dto, String.class);
            } catch (Exception e) {
                System.err.println("Error posting country: " + dto.code() + " -> " + e.getMessage());
            }
        }
    }
}
