package com.pablodll.country_api_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO to encapsulate country related responses
 */
public class CountryResponseDTO {

    @Schema(example = "ESP", description = "ISO 3166-1 alpha-3 country code")
    private String code;

    @Schema(example = "Spain", description = "Country name")
    private String name;

    @Schema(example = "47000000", description = "Country population")
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
