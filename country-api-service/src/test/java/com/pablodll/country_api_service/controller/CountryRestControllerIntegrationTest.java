package com.pablodll.country_api_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablodll.country_api_service.dto.CountryRequestDTO;
import com.pablodll.country_api_service.dto.CountryResponseDTO;
import com.pablodll.country_api_service.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class CountryRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CountryService countryService;

    private final String URL = "/api/v1/data/country";

    private CountryRequestDTO requestDTO;
    private CountryResponseDTO responseDTO;
    private List<CountryResponseDTO> responseDTO_list;

    @BeforeEach
    public void setUp() {
        requestDTO = new CountryRequestDTO("TS1", "Test1", 1000L);
        responseDTO = new CountryResponseDTO("TS1", "Test1", 1000L);

        responseDTO_list = List.of(responseDTO);
    }

    /* ----- GET METHOD TESTS ----- */
    @Test
    public void getAll_successTest() throws Exception {
        when(countryService.getAll()).thenReturn(responseDTO_list);

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(responseDTO.getCode()))
                .andExpect(jsonPath("$.name").value(responseDTO.getName()))
                .andExpect(jsonPath("$.population").value(responseDTO.getPopulation()));

    }

    @Test
    public void getAll_emptyList_successTest() throws Exception {
        when(countryService.getAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }
    /* ---------------------------- */


    /* ----- POST METHOD TESTS ----- */
    @Test
    public void save_successTest() throws Exception {
        when(countryService.save(requestDTO)).thenReturn(responseDTO);

        mockMvc.perform(post(URL)
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(new ObjectMapper().writeValueAsString(requestDTO))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(responseDTO.getCode()))
                .andExpect(jsonPath("$.name").value(responseDTO.getName()))
                .andExpect(jsonPath("$.population").value(responseDTO.getPopulation()));
    }

    @Test
    public void save_serviceThrowsException_returnsInternalServerErrorTest() throws Exception {
        when(countryService.save(any(CountryRequestDTO.class))).thenThrow(new RuntimeException("Unexpectd Error"));

        mockMvc.perform(post(URL)
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isInternalServerError());
    }
    /* ----------------------------- */

}
