package com.pablodll.country_api_service.service;

import com.pablodll.country_api_service.dto.CountryRequestDTO;
import com.pablodll.country_api_service.dto.CountryResponseDTO;
import com.pablodll.country_api_service.mapper.CountryMapper;
import com.pablodll.country_api_service.model.Country;
import com.pablodll.country_api_service.repository.CountryRepository;
import com.pablodll.country_api_service.service.impl.CountryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CountryServiceImplTest {

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryMapper countryMapper;

    @InjectMocks
    private CountryServiceImpl countryService;

    private Country country1;
    private Country country2;

    private CountryResponseDTO country1_responseDTO;
    private CountryResponseDTO country2_responseDTO;

    private CountryRequestDTO country1_requestDTO;
    private CountryRequestDTO country2_requestDTO;


    private List<Country> countryList;
    private List<CountryResponseDTO> countryResponseDTOList;
    private List<CountryRequestDTO> countryRequestDTOList;


    @BeforeEach
    public void setup() {
        country1 = new Country("TS1", "Test1", 1000L);
        country2 = new Country("TS2", "Test2", 2000L);

        country1_responseDTO = new CountryResponseDTO("TS1", "Test1", 1000L);
        country2_responseDTO = new CountryResponseDTO("TS2", "Test2", 2000L);

        country1_requestDTO = new CountryRequestDTO("TS1", "Test1", 1000L);
        country2_requestDTO = new CountryRequestDTO("TS2", "Test2", 2000L);


        countryList = Arrays.asList(country1, country2);
        countryResponseDTOList = Arrays.asList(country1_responseDTO, country2_responseDTO);
        countryRequestDTOList = Arrays.asList(country1_requestDTO, country2_requestDTO);
    }

    /* ----- Create and Update tests ----- */
    @Test
    public void save_notExisting_successTests() {
        when(countryRepository.findById(country1_requestDTO.getCode())).thenReturn(Optional.empty());

        when(countryMapper.requestToEntity(country1_requestDTO)).thenReturn(country1);
        when(countryRepository.save(country1)).thenReturn(country1);
        when(countryMapper.entityToResponse(country1)).thenReturn(country1_responseDTO);

        CountryResponseDTO result = countryService.save(country1_requestDTO);

        // Assert values
        assertEquals(country1_responseDTO.getCode(), result.getCode());
        assertEquals(country1_responseDTO.getName(), result.getName());
        assertEquals(country1_responseDTO.getPopulation(), result.getPopulation());

        // Verify calls
        verify(countryRepository, times(1)).findById(country1.getCode());
        verify(countryRepository, times(1)).save(country1);
    }

    @Test
    public void save_existing_successTest() {
        when(countryRepository.findById(country1_requestDTO.getCode())).thenReturn(Optional.of(country1));

        when(countryRepository.save(country1)).thenReturn(country1);
        when(countryMapper.entityToResponse(country1)).thenReturn(country1_responseDTO);

        CountryResponseDTO result = countryService.save(country1_requestDTO);

        // Assert values
        assertEquals(country1_responseDTO.getCode(), result.getCode());
        assertEquals(country1_responseDTO.getName(), result.getName());
        assertEquals(country1_responseDTO.getPopulation(), result.getPopulation());

        // Verify calls
        verify(countryRepository, times(1)).findById(country1.getCode());
        verify(countryRepository, times(1)).save(country1);
    }

    @Test
    public void save_exceptionFromRepository_failTest() {
        when(countryRepository.findById(country1_requestDTO.getCode())).thenReturn(Optional.of(country1));
        when(countryRepository.save(country1)).thenThrow(new RuntimeException("Error saving data"));

        assertThrows(RuntimeException.class, () -> countryService.save(country1_requestDTO));
    }
    /* ----------------------------------- */

    /* ----- Read tests ----- */
    @Test
    public void getAll_successTest() {
        Page<Country> countryPage = new PageImpl<>(countryList);
        Page<CountryResponseDTO> responseDTOPage = new PageImpl<>(countryResponseDTOList);

        when(countryRepository.findAll(any(Pageable.class))).thenReturn(countryPage);
        when(countryMapper.entityListToReponseList(countryList)).thenReturn(countryResponseDTOList);

        int page = 0;
        int size = 10;
        Page<CountryResponseDTO> result = countryService.getAllPaged(page, size);

        // Assert returned list size
        assertEquals(responseDTOPage.getContent().size(), result.getContent().size());

        // Assert if returned list has same names as expected list (ignore order of data)
        Set<String> expectedNames = responseDTOPage.getContent().stream().map(CountryResponseDTO::getName).collect(Collectors.toSet());
        Set<String> resultNames =  result.getContent().stream().map(CountryResponseDTO::getName).collect(Collectors.toSet());
        assertEquals(expectedNames, resultNames);

        // Verify calls
        verify(countryRepository, times(1)).findAll(any(Pageable.class));
        verify(countryMapper, times(1)).entityListToReponseList(countryList);
    }
    /* ---------------------- */

}
