package com.pablodll.ingestion_service.dto;

public record CountryRequestDTO(
        String code,
        String name,
        Long population
) {}
