package com.management.lead.leadMangement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.lead.leadmangement.LeadMangementApplication;
import com.management.lead.leadmangement.entity.Lead;
import com.management.lead.leadmangement.entity.LeadActivity;
import com.management.lead.leadmangement.repository.LeadCaptureRepository;
import com.management.lead.leadmangement.repository.LeadRepo;
import com.management.lead.leadmangement.repository.UserRepository;
import com.management.lead.leadmangement.services.LeadService;
import com.management.lead.leadmangement.services.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = LeadMangementApplication.class)
@AutoConfigureMockMvc
public class leadpipelineintegration {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    LeadRepo leadRepository;

    @Autowired
    LeadCaptureRepository leadCaptureRepository;

    @Autowired
    private UserService userService;

    @Autowired
    LeadService leadService;

    private static HttpHeaders headers;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization",
                "Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiZXppbyIsImV4cCI6MTY5ODQwMDM2NiwiaWF0IjoxNjk4Mzk2NzY2LCJzY29wZSI6IiJ9.zjdX7IxaZDpzBXJ0c_FQ4jO4SeV05QW5OqwYm4zgrNxosQPSyccvRV821pdjV0ZSKlAqEaSYiIaAx8aKRS_E16bdx4xKQ9poBBYKEtGZ2RquSLAh0C1l7T8SsryzG1pUho7snwz8FLNG5EYpRy9g3w0r-7JGxUuwvfMCT5ODTmjfdETsBd49XmRVRRWVDmagqvynWDKgAuUyCRTb9mYExkCR08tjpVvN8jFjoGoZzckr1l9vykudiiPGmvVFT1_IOyUmNBEPiSemKUuWr4hWtezNkE4QFl6CQw8Sd1khPmMJdKr_V10ayHjCmNH6w3cpUalaTYhltYiEgJYv4BjpHw");
    }

    @BeforeEach
    public void setUp() {
        // Mock the SecurityContext
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getName()).thenReturn("testUser");
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testGetAllLeads() throws Exception {

        ResponseEntity<Lead[]> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/lead/all",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Lead[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Lead[] leads = response.getBody();

        assertEquals(1, leads.length);
    }

    @Test
    public void testUpdateActivityStatusCancel() {
        // Define the URL for the endpoint
        String url = "http://localhost:" + port + "/api/activities/update/status/cancel/1"; // Replace "1" with the
                                                                                            // desired lead ID

        // Send a PUT request to the endpoint
        ResponseEntity<LeadActivity> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,
                new HttpEntity<>(headers), LeadActivity.class);

        // Check the response status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void testUpdateActivityStatus() {
        long capturedLeadId = 1L;
        // Define the URL for the endpoint
        String url = "http://localhost:" + port + "/api/activities/update/status/complete/" + capturedLeadId;
        // desired lead ID

        // Send a PUT request to the endpoint
        ResponseEntity<LeadActivity> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,
                new HttpEntity<>(headers), LeadActivity.class);

        // Check the response status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
