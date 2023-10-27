package com.management.lead.leadmangement.dto;

import java.util.Date;

import com.management.lead.leadmangement.enumconstants.ActivityStatus;
import com.management.lead.leadmangement.enumconstants.ActivityType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ActivityDTO {

    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    @Enumerated(EnumType.STRING)
    private ActivityStatus activityStatus;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @Size(max = 255, message = "Summary must be less than 255 characters")
    private String summary;

    @Size(max = 255, message = "Summary must be less than 255 characters")
    private String detail;

    @Min(0)
    private long assignedUser;
}
