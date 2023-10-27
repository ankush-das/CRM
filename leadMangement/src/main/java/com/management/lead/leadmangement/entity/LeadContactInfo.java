package com.management.lead.leadmangement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lead_contact_data")
@Getter
@Setter
@RequiredArgsConstructor
public class LeadContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String companyName;

    private String jobPosition;

    private String address;

    private String city;

    private String state;

    private Long postalCode;

    private String region;

    @OneToOne
    @JoinColumn(name = "lead_capture_id")
    private LeadCapture leadCaptureData;
}
