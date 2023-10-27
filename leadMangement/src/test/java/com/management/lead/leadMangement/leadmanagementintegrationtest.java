package com.management.lead.leadMangement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.lead.leadmangement.LeadMangementApplication;
import com.management.lead.leadmangement.dto.JwtResponse;
import com.management.lead.leadmangement.dto.LeadCaptureDTO;
import com.management.lead.leadmangement.dto.LeadContactDTO;
import com.management.lead.leadmangement.dto.LeadDTO;
import com.management.lead.leadmangement.dto.LoginRequest;
import com.management.lead.leadmangement.dto.SignUpRequest;
import com.management.lead.leadmangement.entity.Lead;
import com.management.lead.leadmangement.entity.LeadCapture;
import com.management.lead.leadmangement.entity.LeadContactInfo;
import com.management.lead.leadmangement.entity.User;
import com.management.lead.leadmangement.enumconstants.LeadPriority;
import com.management.lead.leadmangement.enumconstants.LeadStage;
import com.management.lead.leadmangement.repository.UserRepository;
import com.management.lead.leadmangement.services.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = LeadMangementApplication.class)
@AutoConfigureMockMvc
public class leadmanagementintegrationtest {

        @LocalServerPort
        private int port;

        @Autowired
        private TestRestTemplate restTemplate;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private UserService userService;

        private static HttpHeaders headers;

        private final ObjectMapper objectMapper = new ObjectMapper();

        @BeforeAll
        public static void init() {
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("Authorization",
                                "Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiZXppbyIsImV4cCI6MTY5ODA4NjYwOCwiaWF0IjoxNjk4MDgzMDA4LCJzY29wZSI6IlJPTEVfVVNFUiJ9.wYShndA5IDtm7PMXwM7oPVD1AbuG_Vx3nzEyaiErgbE8ycxQlDykFP3iOWK_FFAs3E3SaWEoEaw_SGfaVvv9B2XFbUx_Or30xfljQABaDQXeTpiK8oU0i7gYkyl8pu_rvF5y9WWmyqCKWtWCcn80z57-4AT0s7sdnfguifqbpwJwz1pcGucFJnWANRXNhbkWdi32WZtemlW9R7e8i5z-ggSE1FLSpRpxvZAR2buRvbcnFJn2KipgLuBGca3LevAQjbEL27crW1zEdX3iNPc2Vtr7d8NOtVCCkKhBjpqJIyqEgvJpFFwt1Wsbu8c88Egp6MiTOBwmGCkO1gUhcTFaJA");
        }

        private String createURLWithPort() {
                return "http://localhost:" + port + "/api/auth";
        }

        @Test
        @Sql(statements = "INSERT INTO users (name, password, email, companyname,phone, teamname, role) VALUES ('John Doe', 'password123','john.doe@example.com', 'ACME Inc', '1234567890', 'Sales Team', 'USER')")
        @Sql(statements = "DELETE FROM users WHERE name='newuser'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
        void testRegisterUser() throws Exception {

                SignUpRequest signUpRequest = new SignUpRequest();
                signUpRequest.setUsername("newuser");
                signUpRequest.setPassword("password");
                signUpRequest.setEmail("newuser@example.com");
                signUpRequest.setPhone("1234567890");
                signUpRequest.setCompanyname("Company");
                signUpRequest.setTeamname("Team");

                ResponseEntity<String> response = restTemplate.exchange(
                                (createURLWithPort() + "/signup"), HttpMethod.POST,
                                new HttpEntity<>(signUpRequest, headers),
                                String.class);
                String responseBody = response.getBody();
                assertEquals("User registered successfully", responseBody);
                assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testTokenEndpoint() throws Exception {
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setUsername("ezio");
                loginRequest.setPassword("ezio");

                ResponseEntity<JwtResponse> response = restTemplate.exchange(
                                "http://localhost:" + port + "/api/auth/token",
                                HttpMethod.POST,
                                new HttpEntity<>(loginRequest, headers),
                                JwtResponse.class);

                JwtResponse responseBody = response.getBody();

                // Add assertions to validate the response
                assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testCaptureLeadEndpoint() throws Exception {
                LeadCaptureDTO leadCaptureDTO = new LeadCaptureDTO();
                leadCaptureDTO.setName("Jack");
                leadCaptureDTO.setEmail("wow@gmail.com");
                leadCaptureDTO.setPhone("7654321890");
                // Add other necessary fields to the DTO

                ResponseEntity<LeadCapture> response = restTemplate.exchange(
                                "http://localhost:" + port + "/leads/create/capture",
                                HttpMethod.POST,
                                new HttpEntity<>(leadCaptureDTO, headers),
                                LeadCapture.class);

                LeadCapture capturedLead = response.getBody();

                assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testUpdateLeadCaptureEndpoint() throws Exception {

                LeadCaptureDTO leadCaptureDTO = new LeadCaptureDTO();
                leadCaptureDTO.setName("UJack");
                leadCaptureDTO.setEmail("uwow@gmail.com");
                leadCaptureDTO.setPhone("0654321890");
                // Add other necessary fields to the DTO

                ResponseEntity<LeadCapture> response = restTemplate.exchange(
                                "http://localhost:" + port + "/leads/capture/update/"
                                                + 2,
                                HttpMethod.PUT,
                                new HttpEntity<>(leadCaptureDTO, headers),
                                LeadCapture.class);

                LeadCapture updatedLeadCapture = response.getBody();

                assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testGetAllLeadContactsEndpoint() throws Exception {

                ResponseEntity<LeadContactInfo[]> response = restTemplate.exchange(
                                "http://localhost:" + port + "/leads/allcontacts",
                                HttpMethod.GET,
                                new HttpEntity<>(headers),
                                LeadContactInfo[].class);

                assertEquals(HttpStatus.OK, response.getStatusCode());

                LeadContactInfo[] leadContacts = response.getBody();

                assertEquals(2, leadContacts.length);
        }

        @Test
        void testGetAllUsersEndpoint() throws Exception {

                ResponseEntity<User[]> response = restTemplate.exchange(
                                "http://localhost:" + port + "/leads/allusers",
                                HttpMethod.GET,
                                new HttpEntity<>(headers),
                                User[].class);

                assertEquals(HttpStatus.OK, response.getStatusCode());

                User[] leadContacts = response.getBody();

                assertEquals(2, leadContacts.length);
        }

        @Test
        void testGetLeadEndpoint() throws Exception {
                ResponseEntity<Lead> response = restTemplate.exchange(
                                "http://localhost:" + port + "/leads/lead/1",
                                HttpMethod.GET,
                                new HttpEntity<>(headers),
                                Lead.class);

                assertEquals(HttpStatus.OK, response.getStatusCode());

                Lead lead = response.getBody();
        }

        @Test
        void testCreateLeadEndpoint() throws Exception {

                LeadDTO leadDTO = new LeadDTO();

                leadDTO.setQuote("UJack");
                leadDTO.setBudget(new BigDecimal("1000.00")); // Example budget value
                leadDTO.setProbability(40.0);
                leadDTO.setPriority("HIGH");
                leadDTO.setStage("NEW");
                leadDTO.setSource("Web");
                leadDTO.setTag("Important");
                leadDTO.setExpectedClosingDate(new Date());
                leadDTO.setAssignedUser(1); // Example assigned user ID

                ResponseEntity<Lead> response = restTemplate.exchange(
                                "http://localhost:" + port + "/leads/create/3",
                                HttpMethod.POST,
                                new HttpEntity<>(leadDTO, headers),
                                Lead.class);

                Lead lead = response.getBody();

                assertEquals(HttpStatus.CREATED, response.getStatusCode());
        }
}