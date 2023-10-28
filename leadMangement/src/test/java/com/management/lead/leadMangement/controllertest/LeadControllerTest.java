package com.management.lead.leadMangement.controllertest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.management.lead.leadmangement.LeadMangementApplication;
import com.management.lead.leadmangement.controller.LeadController;
import com.management.lead.leadmangement.entity.Lead;
import com.management.lead.leadmangement.entity.LeadCapture;
import com.management.lead.leadmangement.services.LeadService;
import com.management.lead.leadmangement.services.PipelineService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest(classes = LeadMangementApplication.class)
@AutoConfigureMockMvc
class LeadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LeadService leadService;
    @Autowired
    private PipelineService pipelineService;

    @Autowired
    private LeadController leadController;

    @BeforeEach
    void setup() {
    }

    @Test
    @WithMockUser(username = "testuser")
    void testGetUsers() throws Exception {
        mockMvc.perform(get("http://localhost:8080/leads/allusers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "testuser")
    void testUpdateLeadCapture() throws Exception {
        // Create a sample JSON request body for the LeadCaptureDTO
        String requestBody = "{" +
                "\"name\": \"John Doe\"," +
                "\"email\": \"john@example.com\"," +
                "\"phone\": \"+1234567890\"" +
                "}";
        mockMvc.perform(post("http://localhost:8080/leads/create/capture")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "testuser")
    void testGetAllLeadContacts() throws Exception {
        mockMvc.perform(get("http://localhost:8080/leads/allcontacts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "testuser")
    void testGetAllLeads() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/lead/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
