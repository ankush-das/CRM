package com.management.lead.leadMangement.dtotesting;

import com.management.lead.leadmangement.dto.SignUpRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SignUpRequestTest {

    private Validator validator;

    public SignUpRequestTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testBlankUsername() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername(""); // Blank username
        signUpRequest.setPassword("validPassword");
        signUpRequest.setEmail("valid@email.com");
        signUpRequest.setCompanyname("Example Company");
        signUpRequest.setPhone("1234567890");
        signUpRequest.setRole("UserRole");
        signUpRequest.setTeamname("Example Team");

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

        assertEquals(1, violations.size());
    }

    @Test
    void testBlankEmail() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("validUsername");
        signUpRequest.setPassword("validPassword");
        signUpRequest.setEmail(""); // Blank email
        signUpRequest.setCompanyname("Example Company");
        signUpRequest.setPhone("1234567890");
        signUpRequest.setRole("UserRole");
        signUpRequest.setTeamname("Example Team");

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

        assertEquals(1, violations.size());
    }

    @Test
    void testBlankUsernameAndEmail() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername(""); // Blank username
        signUpRequest.setPassword("validPassword");
        signUpRequest.setEmail(""); // Blank email
        signUpRequest.setCompanyname("Example Company");
        signUpRequest.setPhone("1234567890");
        signUpRequest.setRole("UserRole");
        signUpRequest.setTeamname("Example Team");

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

        assertEquals(2, violations.size());
    }

    @Test
    void testGetters() {
        // Create a SignUpRequest instance with sample data
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("testUsername");
        signUpRequest.setPassword("testPassword");
        signUpRequest.setEmail("test@email.com");
        signUpRequest.setCompanyname("testCompany");
        signUpRequest.setPhone("1234567890");
        signUpRequest.setRole("testRole");
        signUpRequest.setTeamname("testTeam");

        // Test the getter methods
        assertEquals("testUsername", signUpRequest.getUsername());
        assertEquals("testPassword", signUpRequest.getPassword());
        assertEquals("test@email.com", signUpRequest.getEmail());
        assertEquals("testCompany", signUpRequest.getCompanyname());
        assertEquals("1234567890", signUpRequest.getPhone());
        assertEquals("testRole", signUpRequest.getRole());
        assertEquals("testTeam", signUpRequest.getTeamname());
    }
}
