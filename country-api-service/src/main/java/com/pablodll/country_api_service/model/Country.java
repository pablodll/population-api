package com.pablodll.country_api_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Persistence class for countries database table
 */
@Entity
@Table(name = "countries")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
