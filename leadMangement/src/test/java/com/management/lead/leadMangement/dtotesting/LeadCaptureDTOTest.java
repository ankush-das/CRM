package com.management.lead.leadMangement.dtotesting;

import com.management.lead.leadmangement.dto.LeadCaptureDTO;
import com.management.lead.leadmangement.entity.LeadCapture;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LeadCaptureDTOTest {

    private Validator validator;

    LeadCaptureDTOTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @CsvSource({
            "John Doe, johndoe@example.com, 1234567890, 0", // Valid data
            "John Doe, invalid-email, 1234567890, 1", // Invalid email
            ", johndoe@example.com, 1234567890, 1", // Blank name
    })
    void testLeadCaptureDTOValidation(String name, String email, String phone, int expectedViolationCount) {
        LeadCaptureDTO leadCaptureDTO = new LeadCaptureDTO();
        leadCaptureDTO.setName(name);
        leadCaptureDTO.setEmail(email);
        leadCaptureDTO.setPhone(phone);

        Set<ConstraintViolation<LeadCaptureDTO>> violations = validator.validate(leadCaptureDTO);

        assertEquals(expectedViolationCount, violations.size());
    }

    @Test
    void testInvalidEmail() {
        LeadCaptureDTO leadCaptureDTO = new LeadCaptureDTO();
        leadCaptureDTO.setName("John Doe");
        leadCaptureDTO.setEmail("invalid-email"); // Invalid email format
        leadCaptureDTO.setPhone("1234567890");

        Set<ConstraintViolation<LeadCaptureDTO>> violations = validator.validate(leadCaptureDTO);

        assertEquals(1, violations.size());
    }

    @Test
    void testBlankName() {
        LeadCaptureDTO leadCaptureDTO = new LeadCaptureDTO();
        leadCaptureDTO.setName(""); // Blank name
        leadCaptureDTO.setEmail("johndoe@example.com");
        leadCaptureDTO.setPhone("1234567890");

        Set<ConstraintViolation<LeadCaptureDTO>> violations = validator.validate(leadCaptureDTO);

        assertEquals(1, violations.size());
    }

    @Test
    void testOnCreate() {
        LeadCapture myEntity = new LeadCapture();
        myEntity.onCreate(); // Simulate the @PrePersist event

        assertNotNull(myEntity.getCreatedDate());
    }

    @Test
    void testGetCreatedDate() {
        LeadCapture myEntity = new LeadCapture();
        myEntity.onCreate(); // Simulate the @PrePersist event

        assertNotNull(myEntity.getCreatedDate());
    }
}
