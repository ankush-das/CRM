package com.management.lead.leadMangement.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.management.lead.leadmangement.LeadMangementApplication;
import com.management.lead.leadmangement.entity.ActivityLog;

@SpringBootTest(classes = LeadMangementApplication.class)
@AutoConfigureMockMvc
class ActivityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "testuser")
    public void testUpdateActivityStatusCancle() throws Exception {
        long capturedLeadId = 1; // Replace with a valid capturedLeadId

        mockMvc.perform(MockMvcRequestBuilders
                .put("http://localhost:8080/api/activities/update/status/cancel/" + capturedLeadId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "testuser")
    public void testUpdateActivityStatus() throws Exception {
        long capturedLeadId = 1; // Replace with a valid capturedLeadId

        mockMvc.perform(MockMvcRequestBuilders
                .put("http://localhost:8080/api/activities/update/status/complete/" + capturedLeadId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

}
