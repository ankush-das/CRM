package com.management.lead.leadMangement.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.management.lead.leadmangement.LeadMangementApplication;
import com.management.lead.leadmangement.dto.LeadDTO;
import com.management.lead.leadmangement.entity.ActivityLog;
import com.management.lead.leadmangement.entity.Lead;
import com.management.lead.leadmangement.enumconstants.LeadStage;
import com.management.lead.leadmangement.exception.InvalidStateException;
import com.management.lead.leadmangement.exception.LeadNotFoundException;
import com.management.lead.leadmangement.repository.LeadCaptureRepository;
import com.management.lead.leadmangement.repository.LeadRepo;
import com.management.lead.leadmangement.services.DashboardService;
import com.management.lead.leadmangement.services.LeadService;
import com.management.lead.leadmangement.services.PipelineService;
import com.management.lead.leadmangement.services.UserService;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
// @SpringBootTest(classes = LeadMangementApplication.class)
class PipelineServiceTest {

    @InjectMocks
    private PipelineService pipelineService;

    @InjectMocks
    private LeadService leadService;

    @Mock
    private UserService userService;

    @Mock
    private DashboardService dashboardService;

    @Mock
    private LeadRepo leadRepo;

    @Mock
    private ActivityLog activityLogRepository;

    @Mock
    private LeadCaptureRepository leadCaptureRepository;

    @BeforeEach
    void setUp() {
        Mockito.reset(leadRepo);
    }

    @Test
    void testFindByStage() {
        // Arrange
        LeadStage stage = LeadStage.NEW;
        Lead lead1 = new Lead();
        lead1.setStage(stage);
        Lead lead2 = new Lead();
        lead2.setStage(stage);

        when(leadRepo.findByStage(stage)).thenReturn(Arrays.asList(lead1, lead2));

        // Act
        List<Lead> result = pipelineService.findByStage(stage);

        // Assert
        assertEquals(2, result.size());
    }

    // Similar test methods for other find methods

    @Test
    void testFindWonOpportunities() {
        // Arrange
        LeadStage stage = LeadStage.WON;
        Lead lead1 = new Lead();
        lead1.setStage(stage);
        Lead lead2 = new Lead();
        lead2.setStage(stage);

        when(leadRepo.findByStage(stage)).thenReturn(Arrays.asList(lead1, lead2));

        // Act
        List<Lead> result = pipelineService.findWonOpportunities();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void testFindAll() {
        List<Lead> leads = new ArrayList<>();
        leads.add(new Lead());
        leads.add(new Lead());

        when(leadRepo.findAll()).thenReturn(leads);

        List<Lead> result = pipelineService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testFindNewOpportunities() {
        List<Lead> leads = new ArrayList<>();
        leads.add(new Lead());
        leads.add(new Lead());

        when(leadRepo.findByStage(LeadStage.NEW)).thenReturn(leads);

        List<Lead> result = pipelineService.findNewOpportunities();

        assertEquals(2, result.size());
    }

    @Test
    void testIsValidNextTransition_ValidTransitions() {
        // NEW to QUALIFIED or CLOSED_LOST should be valid
        assertTrue(pipelineService.isValidNextTransition(LeadStage.NEW, LeadStage.QUALIFIED));
        assertTrue(pipelineService.isValidNextTransition(LeadStage.NEW, LeadStage.CLOSED_LOST));

        // QUALIFIED to PROPOSITION or CLOSED_LOST should be valid
        assertTrue(pipelineService.isValidNextTransition(LeadStage.QUALIFIED, LeadStage.PROPOSITION));
        assertTrue(pipelineService.isValidNextTransition(LeadStage.QUALIFIED, LeadStage.CLOSED_LOST));

        // PROPOSITION to NEGOTIATION or CLOSED_LOST should be valid
        assertTrue(pipelineService.isValidNextTransition(LeadStage.PROPOSITION, LeadStage.NEGOTIATION));
        assertTrue(pipelineService.isValidNextTransition(LeadStage.PROPOSITION, LeadStage.CLOSED_LOST));

        // NEGOTIATION to WON or CLOSED_LOST should be valid
        assertTrue(pipelineService.isValidNextTransition(LeadStage.NEGOTIATION, LeadStage.WON));
        assertTrue(pipelineService.isValidNextTransition(LeadStage.NEGOTIATION, LeadStage.CLOSED_LOST));

        // WON to CLOSED_WON should be valid
        assertTrue(pipelineService.isValidNextTransition(LeadStage.WON, LeadStage.CLOSED_WON));
    }

    @Test
    void testIsValidNextTransition_InvalidTransitions() {
        // CLOSED_LOST or CLOSED_WON to any stage should be invalid
        assertFalse(pipelineService.isValidNextTransition(LeadStage.CLOSED_LOST, LeadStage.NEW));
        assertFalse(pipelineService.isValidNextTransition(LeadStage.CLOSED_WON, LeadStage.PROPOSITION));

        // Any other unknown transitions should be invalid
        assertFalse(pipelineService.isValidNextTransition(LeadStage.NEW, null));
        assertFalse(pipelineService.isValidNextTransition(LeadStage.NEW, LeadStage.WON));
        assertFalse(pipelineService.isValidNextTransition(null, LeadStage.CLOSED_LOST));
    }

    @Test
    void testIsValidPrevTransition_ValidTransitions() {
        // QUALIFIED can transition from NEW or CLOSED_LOST
        assertTrue(pipelineService.isValidPrevTransition(LeadStage.QUALIFIED, LeadStage.NEW));
        assertTrue(pipelineService.isValidPrevTransition(LeadStage.QUALIFIED, LeadStage.CLOSED_LOST));

        // PROPOSITION can transition from QUALIFIED or CLOSED_LOST
        assertTrue(pipelineService.isValidPrevTransition(LeadStage.PROPOSITION, LeadStage.QUALIFIED));
        assertTrue(pipelineService.isValidPrevTransition(LeadStage.PROPOSITION, LeadStage.CLOSED_LOST));

        // NEGOTIATION can transition from PROPOSITION or CLOSED_LOST
        assertTrue(pipelineService.isValidPrevTransition(LeadStage.NEGOTIATION, LeadStage.PROPOSITION));
        assertTrue(pipelineService.isValidPrevTransition(LeadStage.NEGOTIATION, LeadStage.CLOSED_LOST));

        // WON can transition from NEGOTIATION or CLOSED_WON
        assertTrue(pipelineService.isValidPrevTransition(LeadStage.WON, LeadStage.NEGOTIATION));
        assertTrue(pipelineService.isValidPrevTransition(LeadStage.WON, LeadStage.CLOSED_WON));
    }

    @Test
    void testIsValidPrevTransition_InvalidTransitions() {
        // CLOSED_LOST or CLOSED_WON cannot transition from any stage
        assertFalse(pipelineService.isValidPrevTransition(LeadStage.CLOSED_LOST, LeadStage.NEW));
        assertFalse(pipelineService.isValidPrevTransition(LeadStage.CLOSED_WON, LeadStage.PROPOSITION));

        // Any other unknown transitions should be invalid
        assertFalse(pipelineService.isValidPrevTransition(LeadStage.NEW, null));
        assertFalse(pipelineService.isValidPrevTransition(LeadStage.QUALIFIED, LeadStage.WON));
        assertFalse(pipelineService.isValidPrevTransition(null, LeadStage.CLOSED_LOST));
    }

    @Test
    void testCalculateNextStage_ValidTransitions() {
        // NEW should transition to QUALIFIED
        assertEquals(LeadStage.QUALIFIED, pipelineService.calculateNextStage(LeadStage.NEW));

        // QUALIFIED should transition to PROPOSITION
        assertEquals(LeadStage.PROPOSITION, pipelineService.calculateNextStage(LeadStage.QUALIFIED));

        // PROPOSITION should transition to NEGOTIATION
        assertEquals(LeadStage.NEGOTIATION, pipelineService.calculateNextStage(LeadStage.PROPOSITION));

        // NEGOTIATION should transition to WON
        assertEquals(LeadStage.WON, pipelineService.calculateNextStage(LeadStage.NEGOTIATION));

        // WON should transition to CLOSED_WON
        assertEquals(LeadStage.CLOSED_WON, pipelineService.calculateNextStage(LeadStage.WON));

        // CLOSED_LOST or CLOSED_WON should stay in the same state
        assertEquals(LeadStage.CLOSED_LOST, pipelineService.calculateNextStage(LeadStage.CLOSED_LOST));
        assertEquals(LeadStage.CLOSED_WON, pipelineService.calculateNextStage(LeadStage.CLOSED_WON));
    }

    @Test
    void testCalculatePreviousStage_ValidTransitions() {
        // QUALIFIED should transition back to NEW
        assertEquals(LeadStage.NEW, pipelineService.calculatePreviousStage(LeadStage.QUALIFIED));

        // PROPOSITION should transition back to QUALIFIED
        assertEquals(LeadStage.QUALIFIED, pipelineService.calculatePreviousStage(LeadStage.PROPOSITION));

        // NEGOTIATION should transition back to PROPOSITION
        assertEquals(LeadStage.PROPOSITION, pipelineService.calculatePreviousStage(LeadStage.NEGOTIATION));

        // WON should transition back to NEGOTIATION
        assertEquals(LeadStage.NEGOTIATION, pipelineService.calculatePreviousStage(LeadStage.WON));

        // Any other state should stay in the same state
        assertEquals(LeadStage.CLOSED_WON, pipelineService.calculatePreviousStage(LeadStage.CLOSED_WON));
    }

    @Test
    void testLostStageChange_ValidTransition() {
        Long leadId = 1L;
        Lead lead = new Lead();
        lead.setId(leadId);
        lead.setStage(LeadStage.NEW);

        when(leadRepo.findById(leadId)).thenReturn(Optional.of(lead));
        when(leadRepo.save(lead)).thenReturn(lead);

        Lead result = pipelineService.lostStageChange(leadId);

        assertEquals(LeadStage.CLOSED_LOST, result.getStage());
    }

    @Test
    void testLostStageChange_InvalidTransition() {
        Long leadId = 1L;
        Lead lead = new Lead();
        lead.setId(leadId);
        lead.setStage(LeadStage.CLOSED_WON);

        when(leadRepo.findById(leadId)).thenReturn(Optional.of(lead));

        assertThrows(InvalidStateException.class, () -> pipelineService.lostStageChange(leadId));
    }

    @Test
    void testLostStageChange_LeadNotFound() {
        Long leadId = 1L;

        when(leadRepo.findById(leadId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> pipelineService.lostStageChange(leadId));
    }

    @Test
    void testClosedWONStageChange_Success() {
        Long leadId = 1L;
        Lead lead = new Lead();
        lead.setId(leadId);

        // Mock the behavior of leadRepo.findById
        when(leadRepo.findById(leadId)).thenReturn(Optional.of(lead));

        // Call the method
        Lead updatedLead = pipelineService.closedWONStageChange(leadId);

        // Verify that leadRepo.save was called with the updated lead
        verify(leadRepo).save(lead);

        // Verify that the lead's stage is now CLOSED_WON
        assertEquals(LeadStage.CLOSED_WON, updatedLead.getStage());
    }

    @Test
    void testClosedWONStageChange_LeadNotFound() {
        // Arrange
        Long leadId = 1L;
        when(leadRepo.findById(leadId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(LeadNotFoundException.class, () -> pipelineService.closedWONStageChange(leadId));
    }

    @Test
    void testInvalidTransitions() {

        // Test false conditions for all possible transitions
        assertFalse(pipelineService.isValidPrevTransition(LeadStage.QUALIFIED, LeadStage.WON));
        assertFalse(pipelineService.isValidPrevTransition(LeadStage.PROPOSITION, LeadStage.WON));
        assertFalse(pipelineService.isValidPrevTransition(LeadStage.NEGOTIATION, LeadStage.WON));
        assertFalse(pipelineService.isValidPrevTransition(LeadStage.CLOSED_LOST, LeadStage.WON));
        assertFalse(pipelineService.isValidPrevTransition(LeadStage.CLOSED_WON, LeadStage.WON));

    }

    @Test
    void testSoloPatchLeadNotFound() {
        // Mock the leadRepo to return an empty Optional, indicating lead not found
        when(leadRepo.findById(1L)).thenReturn(Optional.empty());

        // Call the method to test
        Lead result = leadService.soloPatch(1L, new LeadDTO());

        // Verify that leadRepo.save was not called (lead not found)
        Mockito.verify(leadRepo, Mockito.never()).save(any());

        // Assert that the result is null (lead not found)
        assertEquals(null, result);
    }

    @Test
    public void testGetLeadCountByStage() {
        String stageName = "NEW"; // Replace with the appropriate stage name

        // Mock the behavior of the repository method
        long expectedCount = 0L;

        // Test the service method
        Long result = dashboardService.getLeadCountByStage(stageName);

        // Assertions
        assertEquals(expectedCount, result);
    }

}
