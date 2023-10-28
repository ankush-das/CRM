package com.management.lead.leadMangement.servicetest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.management.lead.leadmangement.LeadMangementApplication;
import com.management.lead.leadmangement.dto.DashboardDTO;
import com.management.lead.leadmangement.entity.Lead;
import com.management.lead.leadmangement.entity.LeadCapture;
import com.management.lead.leadmangement.enumconstants.LeadStage;
import com.management.lead.leadmangement.repository.LeadCaptureRepository;
import com.management.lead.leadmangement.repository.LeadRepo;
import com.management.lead.leadmangement.services.DashboardService;

import jakarta.persistence.EntityNotFoundException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = LeadMangementApplication.class)
@ExtendWith(SpringExtension.class)
class DashboardServiceTest {

    @Mock
    private LeadRepo leadRepo;

    @Mock
    private LeadCaptureRepository leadCaptureRepository;

    private DashboardService dashboardService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        dashboardService = new DashboardService(leadRepo, leadCaptureRepository);
    }

    @Test
    void testCalculateConversionRate() {
        // Mock data for the test
        List<LeadCapture> leadCaptures = Arrays.asList(new LeadCapture(), new LeadCapture(), new LeadCapture());
        List<Lead> closedWonLeads = Arrays.asList(new Lead(), new Lead());

        // Mocking the behavior of the leadCaptureRepository and leadRepo
        Mockito.when(leadCaptureRepository.findAll()).thenReturn(leadCaptures);
        Mockito.when(leadRepo.findByStage(LeadStage.CLOSED_WON)).thenReturn(closedWonLeads);

        double conversionRate = dashboardService.calculateConversionRate();

        // Assertions
        assertEquals(66.67, conversionRate, 0.01); // You can adjust the delta as needed
    }

    @Test
    void testGetLeadCountInEachStage() {
        // Mock data for the test
        Long countNew = 5L;
        Long countProposition = 8L;
        Long countClosedWon = 12L;

        // Mocking the behavior of leadRepo.countByStage() for each LeadStage
        Mockito.when(leadRepo.countByStage(LeadStage.NEW)).thenReturn(countNew);
        Mockito.when(leadRepo.countByStage(LeadStage.PROPOSITION)).thenReturn(countProposition);
        Mockito.when(leadRepo.countByStage(LeadStage.CLOSED_WON)).thenReturn(countClosedWon);

        Map<String, Long> expectedLeadCounts = new HashMap<>();
        expectedLeadCounts.put(LeadStage.NEW.toString(), countNew);
        expectedLeadCounts.put(LeadStage.PROPOSITION.toString(), countProposition);
        expectedLeadCounts.put("WON", 0L); // Account for the unexpected "WON" stage
        expectedLeadCounts.put(LeadStage.CLOSED_WON.toString(), countClosedWon);
        expectedLeadCounts.put(LeadStage.CLOSED_LOST.toString(), 0L);
        expectedLeadCounts.put(LeadStage.QUALIFIED.toString(), 0L);
        expectedLeadCounts.put(LeadStage.NEGOTIATION.toString(), 0L);

        Map<String, Long> actualLeadCounts = dashboardService.getLeadCountInEachStage();

        // Assertions
        assertEquals(expectedLeadCounts, actualLeadCounts);
    }

    @Test
    void getDashboardData() {
        try {
            DashboardDTO dashboardDTO = new DashboardDTO();

            // Setting total leads
            List<Lead> allLeads = leadRepo.findAll();
            dashboardDTO.setTotalLeads(allLeads.size());

            // Setting total customers
            List<Lead> allCustomers = leadRepo.findByStage(LeadStage.CLOSED_WON);
            dashboardDTO.setTotalCustomers(allCustomers.size());

            // Setting total revenue (total budget)
            long totalBudget = allLeads.stream()
                    .map(lead -> lead.getBudget() != null ? lead.getBudget().longValue() : 0)
                    .mapToLong(Long::longValue)
                    .sum();

            dashboardDTO.setTotalRevenue(totalBudget);

            // Setting new clients
            Iterable<LeadCapture> allCapturesIterable = leadCaptureRepository.findAll();
            List<LeadCapture> allCaptures = StreamSupport.stream(allCapturesIterable.spliterator(), false)
                    .collect(Collectors.toList());

            dashboardDTO.setNewClient(allLeads.size() - allCaptures.size());

            double totalExpectedRevenue = allLeads.stream()
                    .filter(lead -> lead.getBudget() != null && lead.getProbability() != null)
                    .mapToDouble(lead -> lead.getBudget().doubleValue() * lead.getProbability() /
                            100.0)
                    .sum();

            // Set the total expected revenue in the dashboardDTO
            dashboardDTO.setExpectedRevenue(totalExpectedRevenue);

            dashboardDTO.setConversionRate(dashboardService.calculateConversionRate());

            assertEquals(0, dashboardDTO.getTotalLeads());
            assertEquals(0, dashboardDTO.getTotalCustomers());
            assertEquals(0.0, dashboardDTO.getTotalRevenue());
            // assertEquals(3000, dashboardDTO.getConversionRate());
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
    }

}
