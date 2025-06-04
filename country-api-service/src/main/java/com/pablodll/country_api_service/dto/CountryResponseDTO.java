package com.pablodll.country_api_service.dto;

/**
 * DTO to encapsulate country related responses
 */
public class CountryResponseDTO {

    private String name;
    private Long population;

    public CountryResponseDTO() {}

    public CountryResponseDTO(String name, Long population) {
        this.name = name;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
}
