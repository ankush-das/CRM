package com.management.lead.leadmangement.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.lead.leadmangement.dto.DashboardDTO;
import com.management.lead.leadmangement.services.DashboardService;

@CrossOrigin
@RestController
@RequestMapping("/api/dash")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // Mapping to get all opportunities
    @GetMapping("/info")
    public ResponseEntity<DashboardDTO> getAllData() {
        DashboardDTO dashboardDTO = dashboardService.getDashboardData();
        if (dashboardDTO != null) {
            return new ResponseEntity<>(dashboardDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // get lead count in each stage
    @GetMapping("/lead/stage")
    public Map<String, Long> getLeadCountInEachStage() {
        return dashboardService.getLeadCountInEachStage();
    }

    @GetMapping("/revenue")
    public Map<String, Double> getRevenueByStage() {
        return dashboardService.getRevenueByStage();
    }

    @GetMapping("/expectedrevenue")
    public Map<String, Double> getExpectedRevenueByStage() {
        return dashboardService.getExpectedRevenueByStage();
    }

    @GetMapping("/new")
    public Long getNewLeadCount() {
        return dashboardService.getLeadCountByStage("NEW");
    }

    @GetMapping("/won-closed")
    public Long getClosedWonLeadCount() {
        return dashboardService.getLeadCountByStage("CLOSED_WON");
    }

    @GetMapping("/lost-closed")
    public Long getClosedLostLeadCount() {
        return dashboardService.getLeadCountByStage("CLOSED_LOST");
    }
}
