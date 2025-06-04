package com.pablodll.country_api_service.repository;

import com.pablodll.country_api_service.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Country} entity.
 * Basic CRUD methods to manage countries in the DB.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    /// Basic CRUD methods implemented by default by JpaRepository

}
