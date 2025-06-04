package com.pablodll.country_api_service.mapper;

import com.pablodll.country_api_service.dto.CountryRequestDTO;
import com.pablodll.country_api_service.dto.CountryResponseDTO;
import com.pablodll.country_api_service.model.Country;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interface to map DTOs to Entities
 * Implemented via mapstruct
 */
@Mapper(componentModel = "spring")
public interface CountryMapper {

    /// Request mapping logic
    Country requestToEntity(CountryRequestDTO requestDTO);
    CountryRequestDTO entityToRequest(Country entity);

    List<Country> requestListToEntityList(List<CountryRequestDTO> requestDTO_list);
    List<CountryRequestDTO> entityListToRequestList(List<Country> entity_list);

    /// Response mapping logic
    List<Country> responseListToEntityList(List<CountryResponseDTO> responseDTO_list);
    List<CountryResponseDTO> entityListToReponseList(List<Country> entity_list);
}
