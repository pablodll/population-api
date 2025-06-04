package com.pablodll.country_api_service.mapper;

import com.pablodll.country_api_service.dto.CountryRequestDTO;
import com.pablodll.country_api_service.dto.CountryResponseDTO;
import com.pablodll.country_api_service.model.Country;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interface to map DTOs to Entities
 * <p>
 * Implemented via MapStruct. Generated with spring model support.
 */
@Mapper(componentModel = "spring")
public interface CountryMapper {

    // REQUEST MAPPING LOGIC
    /**
     * Converts a {@link CountryRequestDTO} to a {@link Country} entity.
     *
     * @param requestDTO the DTO to convert
     * @return the mapped Country entity
     */
    Country requestToEntity(CountryRequestDTO requestDTO);

    /**
     * Converts a {@link Country} entity to a {@link CountryRequestDTO}.
     *
     * @param entity the entity to convert
     * @return the mapped CountryRequestDTO
     */
    CountryRequestDTO entityToRequest(Country entity);

    /**
     * Converts a list of {@link CountryRequestDTO} to a list of {@link Country} entities.
     *
     * @param requestDTO_list the list of request DTOs
     * @return the list of mapped Country entities
     */
    List<Country> requestListToEntityList(List<CountryRequestDTO> requestDTO_list);

    /**
     * Converts a list of {@link Country} entities to a list of {@link CountryRequestDTO}.
     *
     * @param entity_list the list of Country entities
     * @return the list of mapped CountryRequestDTOs
     */
    List<CountryRequestDTO> entityListToRequestList(List<Country> entity_list);

    // RESPONSE MAPPING LOGIC
    /**
     * Converts a list of {@link CountryResponseDTO} to a list of {@link Country} entities.
     * <p>This method can be useful for testing or reverse transformations, though typically
     * only used internally.</p>
     *
     * @param responseDTO_list the list of response DTOs
     * @return the list of mapped Country entities
     */
    List<Country> responseListToEntityList(List<CountryResponseDTO> responseDTO_list);

    /**
     * Converts a list of {@link Country} entities to a list of {@link CountryResponseDTO}.
     *
     * @param entity_list the list of Country entities
     * @return the list of mapped CountryResponseDTOs
     */
    List<CountryResponseDTO> entityListToReponseList(List<Country> entity_list);
}
