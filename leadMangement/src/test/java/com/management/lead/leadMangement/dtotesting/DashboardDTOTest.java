package com.management.lead.leadMangement.dtotesting;

import com.management.lead.leadmangement.dto.DashboardDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DashboardDTOTest {

    @Test
    void testTotalLeads() {
        DashboardDTO dashboardDTO = new DashboardDTO();
        dashboardDTO.setTotalLeads(100);

        assertEquals(100, dashboardDTO.getTotalLeads());
    }

    @Test
    void testTotalCustomers() {
        DashboardDTO dashboardDTO = new DashboardDTO();
        dashboardDTO.setTotalCustomers(50);

        assertEquals(50, dashboardDTO.getTotalCustomers());
    }

    @Test
    void testTotalRevenue() {
        DashboardDTO dashboardDTO = new DashboardDTO();
        dashboardDTO.setTotalRevenue(5000.0);

        assertEquals(5000.0, dashboardDTO.getTotalRevenue(), 0.001); // Use delta for double comparison
    }

    @Test
    void testNewClient() {
        DashboardDTO dashboardDTO = new DashboardDTO();
        dashboardDTO.setNewClient(10);

        assertEquals(10, dashboardDTO.getNewClient());
    }
}
