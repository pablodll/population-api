package com.pablodll.country_api_service.controller;

import com.pablodll.country_api_service.dto.CountryRequestDTO;
import com.pablodll.country_api_service.dto.CountryResponseDTO;
import com.pablodll.country_api_service.dto.PagedResponseDTO;
import com.pablodll.country_api_service.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Country API", description = "Operations related to countries")
@RestController
@RequestMapping("/api/v1/data/country")
public class CountryRestController {

    private final CountryService countryService;

    @Autowired
    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    // GET
    @Operation(summary = "Retrieves paginated list of countries")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedResponseDTO<CountryResponseDTO>> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<CountryResponseDTO> resultPage = countryService.getAllPaged(page, size);

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
    @Operation(
            summary = "Creates a new country or updates an existing one",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created country successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)),
                    @ApiResponse(responseCode = "409", description = "Database constraint violation", content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)),
                    @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)),
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountryResponseDTO> save(@RequestBody @Valid CountryRequestDTO countryRequestDTO) {
        return new ResponseEntity<>(countryService.save(countryRequestDTO), HttpStatus.CREATED);
    }
}
