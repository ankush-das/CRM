package com.management.lead.leadmangement.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.lead.leadmangement.dto.DashboardDTO;
import com.management.lead.leadmangement.entity.Lead;
import com.management.lead.leadmangement.entity.LeadCapture;
import com.management.lead.leadmangement.enumconstants.LeadStage;
import com.management.lead.leadmangement.repository.LeadCaptureRepository;
import com.management.lead.leadmangement.repository.LeadRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DashboardService {

    @Autowired
    private LeadRepo leadRepo;

    @Autowired
    private LeadCaptureRepository leadCaptureRepository;

    public DashboardService(LeadRepo leadRepo, LeadCaptureRepository leadCaptureRepository) {
        this.leadRepo = leadRepo;
        this.leadCaptureRepository = leadCaptureRepository;
    }

    public DashboardDTO getDashboardData() {
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
                    .mapToLong(lead -> lead.getBudget().longValue()) // Assuming getBudget returns BigDecimal
                    .sum();

            dashboardDTO.setTotalRevenue(totalBudget);

            // Setting new clients
            Iterable<LeadCapture> allCapturesIterable = leadCaptureRepository.findAll();
            List<LeadCapture> allCaptures = StreamSupport.stream(allCapturesIterable.spliterator(), false)
                    .collect(Collectors.toList());

            dashboardDTO.setNewClient(allLeads.size() - allCaptures.size());

            double totalExpectedRevenue = allLeads.stream()
                    .mapToDouble(lead -> lead.getBudget().doubleValue() * lead.getProbability() / 100.0)
                    .sum();
            // Set the total expected revenue in the dashboardDTO
            dashboardDTO.setExpectedRevenue(totalExpectedRevenue);

            dashboardDTO.setConversionRate(calculateConversionRate());
            return dashboardDTO;
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public double calculateConversionRate() {
        Iterable<LeadCapture> allCapturesIterable = leadCaptureRepository.findAll();
        List<LeadCapture> allCaptures = StreamSupport.stream(allCapturesIterable.spliterator(), false)
                .collect(Collectors.toList());

        List<Lead> allCustomers = leadRepo.findByStage(LeadStage.CLOSED_WON);

        if (allCaptures.isEmpty()) {
            return 0.0; // To avoid division by zero
        }

        return ((double) allCustomers.size() / allCaptures.size()) * 100;
    }

    public Map<String, Long> getLeadCountInEachStage() {
        Map<String, Long> leadCounts = new HashMap<>();

        // Loop through each lead stage and get the count for each stage
        for (LeadStage stage : LeadStage.values()) {
            Long count = leadRepo.countByStage(stage);
            leadCounts.put(stage.toString(), count);
        }

        return leadCounts;
    }

    public Map<String, Double> getRevenueByStage() {
        Map<String, Double> revenueByStage = new HashMap<>();
        for (LeadStage stage : LeadStage.values()) {
            Double totalRevenue = calculateTotalRevenueByStage(stage);
            revenueByStage.put(stage.toString(), totalRevenue);
        }
        return revenueByStage;
    }

    public Map<String, Double> getExpectedRevenueByStage() {
        Map<String, Double> expectedRevenueByStage = new HashMap<>();
        for (LeadStage stage : LeadStage.values()) {
            Double expectedRevenue = calculateExpectedRevenueByStage(stage);
            expectedRevenueByStage.put(stage.toString(), expectedRevenue);
        }
        return expectedRevenueByStage;
    }

    public Double calculateTotalRevenueByStage(LeadStage stage) {
        List<Lead> leadsInStage = leadRepo.findByStage(stage);
        return leadsInStage.stream()
                .mapToDouble(lead -> lead.getBudget().doubleValue())
                .sum();
    }

    public Double calculateExpectedRevenueByStage(LeadStage stage) {
        List<Lead> leadsInStage = leadRepo.findByStage(stage);
        return leadsInStage.stream()
                .mapToDouble(lead -> lead.getBudget().doubleValue() * lead.getProbability() / 100.0)
                .sum();
    }

    public Long getLeadCountByStage(String stageName) {
        LeadStage stage = LeadStage.valueOf(stageName); // Convert the stage name to an enum value
        return leadRepo.countByStage(stage);
    }
}
