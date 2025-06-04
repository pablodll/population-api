package com.pablodll.country_api_service.service.impl;

import com.pablodll.country_api_service.dto.CountryRequestDTO;
import com.pablodll.country_api_service.dto.CountryResponseDTO;
import com.pablodll.country_api_service.mapper.CountryMapper;
import com.pablodll.country_api_service.model.Country;
import com.pablodll.country_api_service.repository.CountryRepository;
import com.pablodll.country_api_service.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link CountryService}.
 * Manages the logic related to {@link com.pablodll.country_api_service.model.Country} entities
 */
@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;
    private CountryMapper countryMapper;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    // CREATE / UPDATE LOGIC

    @Override
    public CountryRequestDTO save(CountryRequestDTO countryRequestDTO) {
        Optional<Country> existingCountry = countryRepository.findById(countryRequestDTO.getCode());

        Country newCountry;

        if(existingCountry.isPresent()) {
            newCountry = existingCountry.get();
            newCountry.setName(countryRequestDTO.getName());
            newCountry.setPopulation(countryRequestDTO.getPopulation());
        }
        else {
            newCountry = countryMapper.requestToEntity(countryRequestDTO);
        }

        Country savedCountry = countryRepository.save(newCountry);

        return countryMapper.entityToRequest(savedCountry);
    }

    @Override
    public List<CountryRequestDTO> save(List<CountryRequestDTO> countryRequestDTO_list) {
        return List.of();
    }

    // READ LOGIC

    @Override
    public List<CountryResponseDTO> getAll() {
        List<Country> result = countryRepository.findAll();
        return countryMapper.entityListToReponseList(result);
    }
}
