package com.management.lead.leadmangement.services;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserProductivityDTO {
    private List<String> userNames;
    private List<Integer> activityCounts;
}
