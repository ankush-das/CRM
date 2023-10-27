package com.management.lead.leadmangement.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DashboardDTO {

    private int totalLeads;
    private int totalCustomers;
    private double totalRevenue;
    private int newClient;
    private double expectedRevenue;
    private double conversionRate;
}
