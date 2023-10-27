package com.management.lead.leadMangement.entitytest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.management.lead.leadmangement.entity.LeadActivity;
import com.management.lead.leadmangement.entity.LeadCapture;
import com.management.lead.leadmangement.entity.User;
import com.management.lead.leadmangement.enumconstants.ActivityStatus;
import com.management.lead.leadmangement.enumconstants.ActivityType;

import jakarta.persistence.Table;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class LeadActivityEntityTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testEntityAnnotations() {
        Table tableAnnotation = LeadActivity.class.getAnnotation(Table.class);
        assertNotNull(tableAnnotation);
        assertEquals("leadsActivity", tableAnnotation.name());
    }

    @Test
    void testFieldsAndValidations() {
        LeadActivity leadActivity = new LeadActivity();
        leadActivity.setActivityType(ActivityType.EMAIL);
        leadActivity.setCreatedDate(new Date());
        leadActivity.setDueDate(new Date());
        leadActivity.setSummary("Test Summary");
        leadActivity.setDetail("Test Detail");
        leadActivity.setActivityStatus(ActivityStatus.PENDING);

        User assignedUser = new User();
        assignedUser.setId(1); // Set an assigned user for testing

        User createdByUser = new User();
        createdByUser.setId(2); // Set a created by user for testing

        LeadCapture lead = new LeadCapture();
        lead.setId(3); // Set a lead for testing

        leadActivity.setAssignedUser(assignedUser);
        // leadActivity.setCreatedBy(createdByUser);
        leadActivity.setLead(lead);

        var violations = validator.validate(leadActivity);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testGetId() {
        LeadActivity leadActivity = new LeadActivity();
        leadActivity.setId(1L);

        assertEquals(1L, leadActivity.getId());
    }

    @Test
    void testGetActivityType() {
        LeadActivity leadActivity = new LeadActivity();
        leadActivity.setActivityType(ActivityType.CALL);

        assertEquals(ActivityType.CALL, leadActivity.getActivityType());
    }

    @Test
    void testGetCreatedDate() {
        LeadActivity leadActivity = new LeadActivity();
        Date createdDate = new Date();
        leadActivity.setCreatedDate(createdDate);

        assertEquals(createdDate, leadActivity.getCreatedDate());
    }

    @Test
    void testGetDueDate() {
        LeadActivity leadActivity = new LeadActivity();
        Date dueDate = new Date();
        leadActivity.setDueDate(dueDate);

        assertEquals(dueDate, leadActivity.getDueDate());
    }

    @Test
    void testGetSummary() {
        LeadActivity leadActivity = new LeadActivity();
        leadActivity.setSummary("Test summary");

        assertEquals("Test summary", leadActivity.getSummary());
    }

    @Test
    void testGetDetail() {
        LeadActivity leadActivity = new LeadActivity();
        leadActivity.setDetail("Test detail");

        assertEquals("Test detail", leadActivity.getDetail());
    }

    @Test
    void testGetActivityStatus() {
        LeadActivity leadActivity = new LeadActivity();
        leadActivity.setActivityStatus(ActivityStatus.CANCELLED);

        assertEquals(ActivityStatus.CANCELLED, leadActivity.getActivityStatus());
    }

    @Test
    void testGetAssignedUser() {
        LeadActivity leadActivity = new LeadActivity();
        User user = new User();
        leadActivity.setAssignedUser(user);

        assertEquals(user, leadActivity.getAssignedUser());
    }

    @Test
    void testGetLead() {
        LeadActivity leadActivity = new LeadActivity();
        LeadCapture leadCapture = new LeadCapture();
        leadActivity.setLead(leadCapture);

        assertEquals(leadCapture, leadActivity.getLead());
    }

}
