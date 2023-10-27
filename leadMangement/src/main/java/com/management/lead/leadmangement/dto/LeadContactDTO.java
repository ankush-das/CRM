package com.management.lead.leadmangement.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LeadContactDTO {

    private String companyName;

    private String jobPosition;

    private String address;

    private String city;

    private String state;

    private Long postalCode;

    private String region;
}
