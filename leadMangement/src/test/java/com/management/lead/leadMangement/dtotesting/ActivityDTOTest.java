package com.management.lead.leadMangement.dtotesting;

import com.management.lead.leadmangement.dto.ActivityDTO;
import com.management.lead.leadmangement.enumconstants.ActivityStatus;
import com.management.lead.leadmangement.enumconstants.ActivityType;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ActivityDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidActivityDTO() {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setActivityType(ActivityType.CALL);
        activityDTO.setActivityStatus(ActivityStatus.PENDING);
        activityDTO.setDueDate(new Date());
        activityDTO.setSummary("Valid Summary");
        activityDTO.setDetail("Valid Detail");
        activityDTO.setAssignedUser(12345L);

        Set<ConstraintViolation<ActivityDTO>> violations = validator.validate(activityDTO);

        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidSummary() {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setActivityType(ActivityType.MEETING);
        activityDTO.setActivityStatus(ActivityStatus.PENDING);
        activityDTO.setDueDate(new Date());
        activityDTO.setSummary(
                "This is a very long summary that exceeds the maximum allowed charactersasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        activityDTO.setDetail("Valid Detail");
        activityDTO.setAssignedUser(12345L);

        Set<ConstraintViolation<ActivityDTO>> violations = validator.validate(activityDTO);

        assertEquals(1, violations.size());
    }

    @Test
    void testInvalidDetail() {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setActivityType(ActivityType.EMAIL);
        activityDTO.setActivityStatus(ActivityStatus.PENDING);
        activityDTO.setDueDate(new Date());
        activityDTO.setSummary("Valid Summary");
        activityDTO.setDetail(
                "This is a very long detjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjail that exceeds the maximum allowed characters.");
        activityDTO.setAssignedUser(12345L);

        Set<ConstraintViolation<ActivityDTO>> violations = validator.validate(activityDTO);

        assertEquals(1, violations.size());
    }

    @Test
    void testInvalidAssignedUser() {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setActivityType(ActivityType.CALL);
        activityDTO.setActivityStatus(ActivityStatus.PENDING);
        activityDTO.setDueDate(new Date());
        activityDTO.setSummary("Valid Summary");
        activityDTO.setDetail("Valid Detail");
        activityDTO.setAssignedUser(-100L); // Assigned user should be a positive value

        Set<ConstraintViolation<ActivityDTO>> violations = validator.validate(activityDTO);

        assertEquals(1, violations.size());
    }

    @Test
    void testGetActivityType() {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setActivityType(ActivityType.MEETING);

        assertEquals(ActivityType.MEETING, activityDTO.getActivityType());
    }

    @Test
    void testGetActivityStatus() {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setActivityStatus(ActivityStatus.PENDING);

        assertEquals(ActivityStatus.PENDING, activityDTO.getActivityStatus());
    }

    @Test
    void testGetDueDate() {
        Date dueDate = new Date();
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setDueDate(dueDate);

        assertEquals(dueDate, activityDTO.getDueDate());
    }

    @Test
    void testGetSummary() {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setSummary("Sample summary");

        assertEquals("Sample summary", activityDTO.getSummary());
    }

    @Test
    void testGetDetail() {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setDetail("Sample detail");

        assertEquals("Sample detail", activityDTO.getDetail());
    }

    @Test
    void testGetAssignedUser() {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setAssignedUser(123);

        assertEquals(123, activityDTO.getAssignedUser());
    }
}
