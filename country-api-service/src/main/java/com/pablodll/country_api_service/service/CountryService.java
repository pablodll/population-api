package com.pablodll.country_api_service.service;

import com.pablodll.country_api_service.dto.CountryRequestDTO;
import com.pablodll.country_api_service.dto.CountryResponseDTO;
import com.pablodll.country_api_service.model.Country;

import java.util.List;

/**
 * Service interface for {@link Country}'s entity logic.
 */
public interface CountryService {

    /* ----- CREATE / UPDATE ----- */
    /**
     * Saves or updates a country into the database.
     * <p>
     * If a country with the same code already exists, updates the fields {@code name} and {@code population}. If not, creates a new country.
     *
     * @param countryRequestDTO contains the country data to be saved or updated
     * @return {@link CountryResponseDTO} representing the saved country.
     */
    CountryResponseDTO save(CountryRequestDTO countryRequestDTO);

    /**
     * TODO
     * @param countryRequestDTO_list
     * @return
     */
    List<CountryResponseDTO> save(List<CountryRequestDTO> countryRequestDTO_list);
    /* --------------------------------- */

    /* ----- READ ----- */
    /**
     * Gets all countries currently saved in the database
     *
     * @return {@link List} of {@link CountryResponseDTO} representing every retrieved country.
     */
    List<CountryResponseDTO> getAll();
    /* ---------------------- */


}
