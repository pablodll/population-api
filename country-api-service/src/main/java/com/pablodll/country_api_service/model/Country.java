package com.pablodll.country_api_service.model;

import jakarta.persistence.*;

/**
 * Persistence class for countries database table
 */
@Entity
@Table(name = "countries")
public class Country {

    /**
     * Unique country code
     * Follows ISO 3166-1 alpha-3 (e.g. "ESP", "JPN")
     */
    @Id
    @Column(name = "code", length = 3)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "population", nullable = false)
    private Long population;

    public Country() {}

    public Country(String code, String name, Long population) {
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

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", population=" + population +
                '}';
    }
}
