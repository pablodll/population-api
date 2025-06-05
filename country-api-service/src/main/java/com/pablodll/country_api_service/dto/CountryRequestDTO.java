package com.pablodll.country_api_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO to encapsulate country related requests
 */
public class CountryRequestDTO {

    @NotBlank(message = "value must not be empty")
    @Size(min = 3, max = 3, message = "length must be 3")
    private String code;

    @NotBlank(message = "value must not be empty")
    private String name;

    @NotNull(message = "value must not be null")
    @Min(0)
    private Long population;

    public CountryRequestDTO() {}

    public CountryRequestDTO(String code, String name, Long population) {
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
