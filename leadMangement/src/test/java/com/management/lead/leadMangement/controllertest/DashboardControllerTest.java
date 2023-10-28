package com.management.lead.leadMangement.controllertest;

import static org.mockito.Mockito.when;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.management.lead.leadmangement.LeadMangementApplication;
import com.management.lead.leadmangement.dto.DashboardDTO;
import com.management.lead.leadmangement.enumconstants.LeadStage;
import com.management.lead.leadmangement.services.DashboardService;
import com.management.lead.leadmangement.services.LeadService;

@SpringBootTest(classes = LeadMangementApplication.class)
@AutoConfigureMockMvc
class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DashboardService dashboardService;

    @Before
    public void setUp() {
        // Mock the behavior of the dashboardService
        DashboardDTO mockDashboardDTO = new DashboardDTO();
        when(dashboardService.getDashboardData()).thenReturn(mockDashboardDTO);
    }

    @Test
    @WithMockUser(username = "testuser")
    public void testGetNewLeadCount() throws Exception {
        // Mock the behavior of the dashboardService.getLeadCountByStage method
        when(dashboardService.getLeadCountByStage("NEW")).thenReturn(1L);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/dash/new"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().string("1")); // Verify the response content
    }

    @Test
    @WithMockUser(username = "testuser")
    public void testGetRevenueByStage() throws Exception {
        // Mock the behavior of the dashboardService.getLeadCountByStage method
        when(dashboardService.getLeadCountByStage("QUALIFIED")).thenReturn(1L);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/dash/revenue"))
               .andExpect(MockMvcResultMatchers.status().isOk());
// .andExpect(MockMvcResultMatchers.content().json("[{}]"));    
}

    @Test
    @WithMockUser(username = "testuser")
    public void testgetClosedLostLeadCount() throws Exception {
        // Mock the behavior of the dashboardService.getLeadCountByStage method
        when(dashboardService.getLeadCountByStage("NEW")).thenReturn(1L);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/dash/lost-closed"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().string("0")); // Verify the response content
    }

    @Test
    @WithMockUser(username = "testuser")
    public void testgetClosedWonLeadCount() throws Exception {
        // Mock the behavior of the dashboardService.getLeadCountByStage method
        when(dashboardService.getLeadCountByStage("NEW")).thenReturn(1L);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/dash/won-closed"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().string("0")); // Verify the response content
    }

    @Test
    @WithMockUser(username = "testuser")
    public void testGetAllDataWithExistingData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/dash/info")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "testuser")
    public void testGetAllDataWithNoData() throws Exception {
        // Mock the behavior of the dashboardService to return null
        when(dashboardService.getDashboardData()).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/dash/info")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
