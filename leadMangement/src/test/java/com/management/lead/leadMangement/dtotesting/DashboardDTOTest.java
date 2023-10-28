package com.management.lead.leadMangement.dtotesting;

import com.management.lead.leadmangement.LeadMangementApplication;
import com.management.lead.leadmangement.dto.DashboardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = LeadMangementApplication.class)
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

    @Test
    public void testExpectedRevenueGetter() {
        DashboardDTO dto = new DashboardDTO();
        dto.setExpectedRevenue(500.0);
        assertEquals(500.0, dto.getExpectedRevenue(), 0.001); // Using delta for double comparison
    }

    @Test
    public void testConversionRateGetter() {
        DashboardDTO dto = new DashboardDTO();
        dto.setConversionRate(0.15);
        assertEquals(0.15, dto.getConversionRate(), 0.001); // Using delta for double comparison
    }
}
