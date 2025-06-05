package com.pablodll.country_api_service.controller;

import com.pablodll.country_api_service.dto.CountryRequestDTO;
import com.pablodll.country_api_service.dto.CountryResponseDTO;
import com.pablodll.country_api_service.dto.PagedResponseDTO;
import com.pablodll.country_api_service.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/data/country")
public class CountryRestController {

    private final CountryService countryService;

    @Autowired
    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    // GET
    /**
     * Retrieves all countries stored in the system.
     *
     * @return list of countries as response DTOs
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedResponseDTO<CountryResponseDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<CountryResponseDTO> resultPage = countryService.getAll(page, size);

        PagedResponseDTO<CountryResponseDTO> response = new PagedResponseDTO<>(
                resultPage.getContent(),
                resultPage.getNumber(),
                resultPage.getSize(),
                resultPage.getTotalElements(),
                resultPage.getTotalPages(),
                resultPage.isLast()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // POST
    /**
     * Stores a new country or updates it if it already exists.
     *
     * @param countryRequestDTO the country data to be saved
     * @return the created or updated country as a response DTO
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountryResponseDTO> save(@RequestBody @Valid CountryRequestDTO countryRequestDTO) {
        return new ResponseEntity<>(countryService.save(countryRequestDTO), HttpStatus.CREATED);
    }
}
