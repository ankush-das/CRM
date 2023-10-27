package com.management.lead.leadMangement.entitytest;

import com.management.lead.leadmangement.entity.Lead;
import com.management.lead.leadmangement.entity.User;
import com.management.lead.leadmangement.enumconstants.LeadPriority;
import com.management.lead.leadmangement.enumconstants.LeadStage;
import com.management.lead.leadmangement.entity.LeadContactInfo;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.Table;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

class LeadEntityTest {

    @Test
    void testEntityAnnotations() {
        Table tableAnnotation = Lead.class.getAnnotation(Table.class);
        assertNotNull(tableAnnotation);
        assertEquals("leads", tableAnnotation.name());
    }

    @Test
    void testFieldsAnnotations() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Lead lead = new Lead();
        lead.setQuote("Test Quote");
        lead.setBudget(new BigDecimal("1000.00"));
        lead.setProbability(30.0);
        lead.setPriority(LeadPriority.HIGH);
        lead.setStage(LeadStage.NEW);
        lead.setSource("Web");
        lead.setTag("Test Tag");
        lead.setAssignedUser(new User());
        lead.setExpectedClosingDate(new Date());
        lead.setLeadContact(new LeadContactInfo());

        var violations = validator.validate(lead);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testGetId() {
        Lead lead = new Lead();
        lead.setId(1L);

        assertEquals(1L, lead.getId());
    }

    @Test
    void testGetQuote() {
        Lead lead = new Lead();
        lead.setQuote("Test Quote");

        assertEquals("Test Quote", lead.getQuote());
    }

    @Test
    void testGetBudget() {
        Lead lead = new Lead();
        BigDecimal budget = new BigDecimal("1000.50");
        lead.setBudget(budget);

        assertEquals(budget, lead.getBudget());
    }

    @Test
    void testGetProbability() {
        Lead lead = new Lead();
        lead.setProbability(30.0);

        assertEquals(30.0, lead.getProbability());
    }

    @Test
    void testGetPriority() {
        Lead lead = new Lead();
        lead.setPriority(LeadPriority.LOW);

        assertEquals(LeadPriority.LOW, lead.getPriority());
    }

    @Test
    void testGetStage() {
        Lead lead = new Lead();
        lead.setStage(LeadStage.NEW);

        assertEquals(LeadStage.NEW, lead.getStage());
    }

    @Test
    void testGetSource() {
        Lead lead = new Lead();
        lead.setSource("Web");

        assertEquals("Web", lead.getSource());
    }

    @Test
    void testGetTag() {
        Lead lead = new Lead();
        lead.setTag("Tag1");

        assertEquals("Tag1", lead.getTag());
    }

    @Test
    void testGetAssignedUser() {
        Lead lead = new Lead();
        User user = new User();
        lead.setAssignedUser(user);

        assertNotNull(lead.getAssignedUser());
    }

    @Test
    void testGetExpectedClosingDate() {
        Lead lead = new Lead();
        Date expectedClosingDate = new Date();
        lead.setExpectedClosingDate(expectedClosingDate);

        assertEquals(expectedClosingDate, lead.getExpectedClosingDate());
    }

    @Test
    void testGetLeadContact() {
        Lead lead = new Lead();
        LeadContactInfo leadContactInfo = new LeadContactInfo();
        lead.setLeadContact(leadContactInfo);

        assertNotNull(lead.getLeadContact());
    }

}
