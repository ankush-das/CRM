package com.management.lead.leadMangement.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.lead.leadmangement.LeadMangementApplication;
import com.management.lead.leadmangement.controller.PipelineController;
import com.management.lead.leadmangement.entity.Lead;
import com.management.lead.leadmangement.services.PipelineService;

@SpringBootTest(classes = LeadMangementApplication.class)
@AutoConfigureMockMvc
public class PipeLineControllerTest {

    @InjectMocks
    private PipelineController pipelineController;

    @Mock
    private PipelineService pipelineService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllLeads() {
        // Create a sample list of leads
        List<Lead> leads = new ArrayList<>();
        // Add some sample leads to the list

        // Mock the behavior of the pipelineService.findAll() method
        Mockito.when(pipelineService.findAll()).thenReturn(leads);

        // Call the controller method
        ResponseEntity<List<Lead>> response = pipelineController.getAllLeads();

        // Verify that the pipelineService.findAll() method was called
        Mockito.verify(pipelineService).findAll();

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(leads, response.getBody());
    }

    @Test
    public void testNextLeadStageTransition() {
        // Define a lead ID for testing
        Long leadId = 1L;

        // Create a sample lead for the test
        Lead lead = new Lead();
        // Set lead properties as needed

        // Mock the behavior of the pipelineService.nextstageChange(leadId) method
        Mockito.when(pipelineService.nextstageChange(leadId)).thenReturn(lead);

        // Call the controller method
        ResponseEntity<Lead> response = pipelineController.nextLeadStageTransition(leadId);

        // Verify that the pipelineService.nextstageChange(leadId) method was called
        Mockito.verify(pipelineService).nextstageChange(leadId);

        // Assert the response
        assertEquals(200, response.getStatusCodeValue()); // Assuming HttpStatus.OK is returned
        assertEquals(lead, response.getBody());
    }

    @Test
    public void testPrevLeadStageTransition() {
        // Define a lead ID for testing
        Long leadId = 1L;

        // Create a sample lead for the test
        Lead lead = new Lead();
        // Set lead properties as needed

        // Mock the behavior of the pipelineService.previousStageChange(leadId) method
        Mockito.when(pipelineService.previousStageChange(leadId)).thenReturn(lead);

        // Call the controller method
        ResponseEntity<Lead> response = pipelineController.prevLeadStageTransition(leadId);

        // Verify that the pipelineService.previousStageChange(leadId) method was called
        Mockito.verify(pipelineService).previousStageChange(leadId);

        // Assert the response
        assertEquals(200, response.getStatusCodeValue()); // Assuming HttpStatus.OK is returned
        assertEquals(lead, response.getBody());
    }

    @Test
    public void testLostLeadStageTransition() {
        // Define a lead ID for testing
        Long leadId = 1L;

        // Create a sample lead for the test
        Lead lead = new Lead();
        // Set lead properties as needed

        // Mock the behavior of the pipelineService.lostStageChange(leadId) method
        Mockito.when(pipelineService.lostStageChange(leadId)).thenReturn(lead);

        // Call the controller method
        ResponseEntity<Lead> response = pipelineController.lostLeadStageTransition(leadId);

        // Verify that the pipelineService.lostStageChange(leadId) method was called
        Mockito.verify(pipelineService).lostStageChange(leadId);

        // Assert the response
        assertEquals(200, response.getStatusCodeValue()); // Assuming HttpStatus.OK is returned
        assertEquals(lead, response.getBody());
    }

    @Test
    public void testClosedWONLeadStageTransition() {
        // Define a lead ID for testing
        Long leadId = 1L;

        // Create a sample lead for the test
        Lead lead = new Lead();
        // Set lead properties as needed

        // Mock the behavior of the pipelineService.closedWONStageChange(leadId) method
        Mockito.when(pipelineService.closedWONStageChange(leadId)).thenReturn(lead);

        // Call the controller method
        ResponseEntity<Lead> response = pipelineController.closedWONLeadStageTransition(leadId);

        // Verify that the pipelineService.closedWONStageChange(leadId) method was
        // called
        Mockito.verify(pipelineService).closedWONStageChange(leadId);

        // Assert the response
        assertEquals(200, response.getStatusCodeValue()); // Assuming HttpStatus.OK is returned
        assertEquals(lead, response.getBody());
    }

    @Test
    public void testSearchLeadsByCompanyName() {
        // Define a sample company name for testing
        String companyName = "ExampleCompany";

        // Create a sample list of leads for the test
        List<Lead> leads = new ArrayList<>();
        // Add some sample leads to the list

        // Mock the behavior of the
        // pipelineService.searchLeadsByCompanyName(companyName) method
        Mockito.when(pipelineService.searchLeadsByCompanyName(companyName)).thenReturn(leads);

        // Call the controller method
        List<Lead> response = pipelineController.searchLeadsByCompanyName(companyName);

        // Verify that the pipelineService.searchLeadsByCompanyName(companyName) method
        // was called
        Mockito.verify(pipelineService).searchLeadsByCompanyName(companyName);

        // Assert the response
        assertEquals(leads.size(), response.size());
        assertEquals(leads, response);
    }

    @Test
    public void testSearchLeadsBySource() {
        // Define a sample source for testing
        String source = "ExampleSource";

        // Create a sample list of leads for the test
        List<Lead> leads = new ArrayList<>();
        // Add some sample leads to the list

        // Mock the behavior of the pipelineService.searchLeadsBySource(source) method
        Mockito.when(pipelineService.searchLeadsBySource(source)).thenReturn(leads);

        // Call the controller method
        List<Lead> response = pipelineController.searchLeadsBySource(source);

        // Verify that the pipelineService.searchLeadsBySource(source) method was called
        Mockito.verify(pipelineService).searchLeadsBySource(source);

        // Assert the response
        assertEquals(leads.size(), response.size());
        assertEquals(leads, response);
    }

}
