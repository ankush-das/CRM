package com.management.lead.leadMangement.dtotesting;

import com.management.lead.leadmangement.dto.JwtResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JwtResponseTest {

    @Test
    void testConstructorAndGetterMethods() {
        String accessToken = "myAccessToken";
        String username = "myUsername";
        String role = "myRole";

        JwtResponse jwtResponse = new JwtResponse(accessToken, username, role);

        // Check the values set in the constructor
        assertEquals(accessToken, jwtResponse.getToken());
        assertEquals(username, jwtResponse.getUsername());
        assertEquals(role, jwtResponse.getRole());
    }

    @Test
    void testSetterMethods() {
        JwtResponse jwtResponse = new JwtResponse("initialToken", "initialUsername", "initialRole");

        // Use setter methods to update the values
        jwtResponse.setToken("newToken");
        jwtResponse.setUsername("newUsername");
        jwtResponse.setRole("newRole");

        assertEquals("newToken", jwtResponse.getToken());
        assertEquals("newUsername", jwtResponse.getUsername());
        assertEquals("newRole", jwtResponse.getRole());
    }
}
