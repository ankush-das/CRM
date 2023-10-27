package com.management.lead.leadMangement.entitytest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.management.lead.leadmangement.entity.ActivityLog;
import com.management.lead.leadmangement.entity.LeadCapture;
import com.management.lead.leadmangement.entity.User;

import jakarta.persistence.Table;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class ActivityLogEntityTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testEntityAnnotations() {
        Table tableAnnotation = ActivityLog.class.getAnnotation(Table.class);
        assertNotNull(tableAnnotation);
        assertEquals("ActivityLog", tableAnnotation.name());
    }

    @Test
    void testFieldsAndValidations() {
        ActivityLog activityLog = new ActivityLog();
        activityLog.setLogDate(new Date());
        activityLog.setDetails("Test activity log");

        User user = new User();
        user.setId(1); // Set a user for testing

        LeadCapture lead = new LeadCapture();
        lead.setId(2); // Set a lead for testing

        activityLog.setUser(user);
        activityLog.setLead(lead);

        var violations = validator.validate(activityLog);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testGetId() {
        ActivityLog activityLog = new ActivityLog();
        activityLog.setId(1L);

        assertEquals(1L, activityLog.getId());
    }

    @Test
    void testGetLogDate() {
        ActivityLog activityLog = new ActivityLog();
        Date logDate = new Date();
        activityLog.setLogDate(logDate);

        assertEquals(logDate, activityLog.getLogDate());
    }

    @Test
    void testGetDetails() {
        ActivityLog activityLog = new ActivityLog();
        activityLog.setDetails("Test details");

        assertEquals("Test details", activityLog.getDetails());
    }

    @Test
    void testGetUser() {
        ActivityLog activityLog = new ActivityLog();
        User user = new User();
        activityLog.setUser(user);

        assertEquals(user, activityLog.getUser());
    }

    @Test
    void testGetLead() {
        ActivityLog activityLog = new ActivityLog();
        LeadCapture leadCapture = new LeadCapture();
        activityLog.setLead(leadCapture);

        assertEquals(leadCapture, activityLog.getLead());
    }

}
