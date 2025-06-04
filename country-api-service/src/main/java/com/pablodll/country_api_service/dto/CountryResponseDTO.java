package com.pablodll.country_api_service.dto;

/**
 * DTO to encapsulate country related responses
 */
public class CountryResponseDTO {

    private String code;
    private String name;
    private Long population;

    public CountryResponseDTO() {}

    public CountryResponseDTO(String code, String name, Long population) {
        this.code = code;
        this.name = name;
        this.population = population;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
