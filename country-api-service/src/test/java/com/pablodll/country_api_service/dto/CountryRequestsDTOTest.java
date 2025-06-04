package com.pablodll.country_api_service.dto;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CountryRequestsDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    ///  Code field test
    @Test
    void blankCode_failTest() {
        CountryRequestDTO dto = new CountryRequestDTO("", "example", 1000L);

        Set<ConstraintViolation<CountryRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void longerCode_failTest() {
        CountryRequestDTO dto = new CountryRequestDTO("EXAMP", "example", 1000L);

        Set<ConstraintViolation<CountryRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shorterCode_failTest() {
        CountryRequestDTO dto = new CountryRequestDTO("EX", "example", 1000L);

        Set<ConstraintViolation<CountryRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    /// Name field tests
    @Test
    void blankName_failTest() {
        CountryRequestDTO dto = new CountryRequestDTO("EXP", "", 1000L);

        Set<ConstraintViolation<CountryRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    ///  Population field tests
    @Test
    void nullPopulation_failTest() {
        CountryRequestDTO dto = new CountryRequestDTO("EXP", "example", null);

        Set<ConstraintViolation<CountryRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void negativePopulation_failTest() {
        CountryRequestDTO dto = new CountryRequestDTO("EXP", "example", -1000L);

        Set<ConstraintViolation<CountryRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    ///  General test
    @Test
    void creation_successTest() {
        CountryRequestDTO dto = new CountryRequestDTO("EXP", "example", 1000L);

        Set<ConstraintViolation<CountryRequestDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

}
