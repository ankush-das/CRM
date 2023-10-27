package com.management.lead.leadMangement.dtotesting;

import com.management.lead.leadmangement.dto.LoginRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginRequestTest {

    private Validator validator;

    public LoginRequestTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @CsvSource({
            "validUsername, validPassword, 0", // Valid data
            ", validPassword, 1", // Blank username
            "validUsername, , 1", // Blank password
            ", , 2" // Blank username and password
    })
    void testLoginRequestValidation(String username, String password, int expectedViolationCount) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);

        assertEquals(expectedViolationCount, violations.size());
    }

    @Test
    void testBlankPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("validUsername");
        loginRequest.setPassword(""); // Blank password

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);

        assertEquals(1, violations.size());
    }

    @Test
    void testBlankUsernameAndPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(""); // Blank username
        loginRequest.setPassword(""); // Blank password

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);

        assertEquals(2, violations.size());
    }

    @Test
    void testGetUsername() {
        // Create a LoginRequest instance with a username
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUsername");

        // Test the getUsername method
        assertEquals("testUsername", loginRequest.getUsername());
    }

    @Test
    void testGetPassword() {
        // Create a LoginRequest instance with a password
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("testPassword");

        // Test the getPassword method
        assertEquals("testPassword", loginRequest.getPassword());
    }
}
