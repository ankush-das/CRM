package com.management.lead.leadMangement.dtotesting;

import com.management.lead.leadmangement.dto.LeadContactDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LeadContactDTOTest {

    @Test
    void testGetterAndSetterMethods() {
        LeadContactDTO leadContactDTO = new LeadContactDTO();
        leadContactDTO.setCompanyName("Example Company");
        leadContactDTO.setJobPosition("Manager");
        leadContactDTO.setAddress("123 Main Street");
        leadContactDTO.setCity("Sample City");
        leadContactDTO.setState("Sample State");
        leadContactDTO.setPostalCode(12345L);
        leadContactDTO.setRegion("Sample Region");

        // Check the values using getter methods
        assertEquals("Example Company", leadContactDTO.getCompanyName());
        assertEquals("Manager", leadContactDTO.getJobPosition());
        assertEquals("123 Main Street", leadContactDTO.getAddress());
        assertEquals("Sample City", leadContactDTO.getCity());
        assertEquals("Sample State", leadContactDTO.getState());
        assertEquals(12345L, leadContactDTO.getPostalCode());
        assertEquals("Sample Region", leadContactDTO.getRegion());
    }
}
