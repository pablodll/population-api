package com.pablodll.ingestion_service.dto;

public record RestCountryResponse(
        Name name,
        String cca3,
        Long population
) {
    public record Name(
            String common
    ) {}
}
