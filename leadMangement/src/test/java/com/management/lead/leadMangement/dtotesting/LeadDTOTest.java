package com.management.lead.leadMangement.dtotesting;

import com.management.lead.leadmangement.dto.LeadDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LeadDTOTest {

    private Validator validator;

    LeadDTOTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidLeadDTO() {
        LeadDTO leadDTO = new LeadDTO();
        leadDTO.setQuote("Sample Quote");
        leadDTO.setBudget(new BigDecimal("1000.00"));
        leadDTO.setProbability(30.0);
        leadDTO.setPriority("Urgent");
        leadDTO.setStage("Prospect");
        leadDTO.setSource("Referral");
        leadDTO.setTag("Tag1");
        leadDTO.setExpectedClosingDate(new Date());
        leadDTO.setAssignedUser(12345L);

        Set<ConstraintViolation<LeadDTO>> violations = validator.validate(leadDTO);

        assertEquals(0, violations.size());
    }

    @Test
    void testInvalidBudget() {
        LeadDTO leadDTO = new LeadDTO();
        leadDTO.setQuote("Sample Quote");
        leadDTO.setBudget(new BigDecimal("-100.00")); // Invalid budget (negative value)
        leadDTO.setProbability(30.0);
        leadDTO.setPriority("Urgent");
        leadDTO.setStage("Prospect");
        leadDTO.setSource("Referral");
        leadDTO.setTag("Tag1");
        leadDTO.setExpectedClosingDate(new Date());
        leadDTO.setAssignedUser(12345L);

        Set<ConstraintViolation<LeadDTO>> violations = validator.validate(leadDTO);

        assertEquals(1, violations.size());
    }

    @Test
    void testNullExpectedClosingDate() {
        LeadDTO leadDTO = new LeadDTO();
        leadDTO.setQuote("Sample Quote");
        leadDTO.setBudget(new BigDecimal("1000.00"));
        leadDTO.setProbability(30.0);
        leadDTO.setPriority("Urgent");
        leadDTO.setStage("Prospect");
        leadDTO.setSource("Referral");
        leadDTO.setTag("Tag1");
        // Null expectedClosingDate

        Set<ConstraintViolation<LeadDTO>> violations = validator.validate(leadDTO);

        assertEquals(1, violations.size());
    }

    @Test
    void testGetQuote() {
        LeadDTO leadDTO = new LeadDTO();
        leadDTO.setQuote("Sample quote");

        assertEquals("Sample quote", leadDTO.getQuote());
    }

    @Test
    void testGetBudget() {
        BigDecimal budget = new BigDecimal("1000.00");
        LeadDTO leadDTO = new LeadDTO();
        leadDTO.setBudget(budget);

        assertEquals(budget, leadDTO.getBudget());
    }

    @Test
    void testGetProbability() {
        LeadDTO leadDTO = new LeadDTO();
        leadDTO.setProbability(30.0);

        assertEquals(30.0, leadDTO.getProbability());
    }

    @Test
    void testGetPriority() {
        LeadDTO leadDTO = new LeadDTO();
        leadDTO.setPriority("Urgent");

        assertEquals("Urgent", leadDTO.getPriority());
    }

    @Test
    void testGetStage() {
        LeadDTO leadDTO = new LeadDTO();
        leadDTO.setStage("Qualified");

        assertEquals("Qualified", leadDTO.getStage());
    }

    @Test
    void testGetSource() {
        LeadDTO leadDTO = new LeadDTO();
        leadDTO.setSource("Website");

        assertEquals("Website", leadDTO.getSource());
    }

    @Test
    void testGetTag() {
        LeadDTO leadDTO = new LeadDTO();
        leadDTO.setTag("Important");

        assertEquals("Important", leadDTO.getTag());
    }

    @Test
    void testGetExpectedClosingDate() {
        Date expectedClosingDate = new Date();
        LeadDTO leadDTO = new LeadDTO();
        leadDTO.setExpectedClosingDate(expectedClosingDate);

        assertEquals(expectedClosingDate, leadDTO.getExpectedClosingDate());
    }

    @Test
    void testGetAssignedUser() {
        LeadDTO leadDTO = new LeadDTO();
        leadDTO.setAssignedUser(123);

        assertEquals(123, leadDTO.getAssignedUser());
    }

}
