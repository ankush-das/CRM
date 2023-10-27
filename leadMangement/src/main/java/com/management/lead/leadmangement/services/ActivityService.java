package com.management.lead.leadmangement.services;

import java.util.Optional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.management.lead.leadmangement.dto.ActivityDTO;
import com.management.lead.leadmangement.entity.ActivityLog;
import com.management.lead.leadmangement.entity.LeadActivity;
import com.management.lead.leadmangement.entity.LeadCapture;
import com.management.lead.leadmangement.entity.User;
import com.management.lead.leadmangement.enumconstants.ActivityStatus;
import com.management.lead.leadmangement.repository.ActivityLogRepository;
import com.management.lead.leadmangement.repository.ActivityRepository;
import com.management.lead.leadmangement.repository.LeadCaptureRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ActivityService {

        private ActivityRepository activityRepository;
        private UserService userService;
        private LeadCaptureRepository leadCaptureRepository;
        private ActivityLogRepository activityLogRepository;

        public ActivityService(@Autowired ActivityRepository activityRepository, UserService userService,
                        LeadCaptureRepository leadCaptureRepository,
                        ActivityLogRepository activityLogRepository) {
                this.activityRepository = activityRepository;
                this.userService = userService;
                this.leadCaptureRepository = leadCaptureRepository;
                this.activityLogRepository = activityLogRepository;
        }

        public LeadActivity createActivity(long capturedLeadId, ActivityDTO activityDTO) {

                // get assigned user
                Optional<User> userOptional = userService.getById(activityDTO.getAssignedUser());
                User assignedUser = userOptional.orElseThrow(() -> new EntityNotFoundException("User not found"));

                // get logged in user
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String username = authentication.getName();
                Optional<User> loggedinUser = userService.getByName(username);

                LeadCapture leadCaptureData = leadCaptureRepository.findById(capturedLeadId)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Lead with ID " + capturedLeadId + " not found"));

                LeadActivity leadActivity = new LeadActivity();
                leadActivity.setActivityType(activityDTO.getActivityType());
                leadActivity.setActivityStatus(activityDTO.getActivityStatus());
                leadActivity.setDueDate(activityDTO.getDueDate());
                leadActivity.setDetail(activityDTO.getDetail());
                leadActivity.setSummary(activityDTO.getSummary());
                leadActivity.setLead(leadCaptureData);
                leadActivity.setAssignedUser(assignedUser);
                leadActivity.setCreatedDate(new Date());

                // creating a activity log
                ActivityLog activityLog = new ActivityLog();
                activityLog.setLogDate(new Date());
                activityLog.setDetails("Performed activity: " +
                                activityDTO.getActivityType());

                if (loggedinUser.isPresent()) {

                        activityLog.setUser(loggedinUser.get());
                }
                activityLog.setLead(leadCaptureData);

                // Save the ActivityLog
                activityLogRepository.save(activityLog);

                return activityRepository.save(leadActivity);
        }

        public List<LeadActivity> allActivitybyId(long capturedLeadId) {

                // get logged in user
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String username = authentication.getName();
                User user = userService.getByName(username)
                                .orElseThrow(() -> new EntityNotFoundException("User with username " +
                                                username + " not found"));

                LeadCapture leadCaptureData = leadCaptureRepository.findById(capturedLeadId)
                                .orElseThrow(() -> new EntityNotFoundException("Lead with ID " +
                                                capturedLeadId + " not found"));

                // Retrieve all activities between the lead and the user
                return activityRepository.findActivitiesByAssignedUserAndLead(user,
                                leadCaptureData);
        }

        public List<LeadActivity> allActivity() {
                // Retrieve all activities between the lead and the user
                Iterable<LeadActivity> activities = activityRepository.findAll();
                List<LeadActivity> activityList = new ArrayList<>();

                activities.forEach(activityList::add);

                return activityList;
        }

        public List<ActivityLog> allLogs(long capturedLeadId) {

                // get logged in user
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String username = authentication.getName();
                User user = userService.getByName(username)
                                .orElseThrow(() -> new EntityNotFoundException("User with username " +
                                                username + " not found"));

                LeadCapture leadCaptureData = leadCaptureRepository.findById(capturedLeadId)
                                .orElseThrow(() -> new EntityNotFoundException("Lead with ID " +
                                                capturedLeadId + " not found"));

                // Retrieve all activities between the lead and the user
                return activityLogRepository.findAllByUserAndLead(user,
                                leadCaptureData);
        }

        public LeadActivity updateStatus(long capturedLeadId) {
                // Retrieve the activity by capturedLeadId from your repository
                Optional<LeadActivity> optionalActivity = activityRepository.findById(capturedLeadId);

                if (optionalActivity.isPresent()) {
                        LeadActivity activity = optionalActivity.get();
                        activity.setActivityStatus(ActivityStatus.COMPLETED);

                        return activityRepository.save(activity);
                } else {
                        throw new EntityNotFoundException("Activity not found");
                }
        }

        public LeadActivity cancelStatus(long capturedLeadId) {
                // Retrieve the activity by capturedLeadId from your repository
                Optional<LeadActivity> optionalActivity = activityRepository.findById(capturedLeadId);
                if (optionalActivity.isPresent()) {
                        LeadActivity activity = optionalActivity.get();
                        activity.setActivityStatus(ActivityStatus.CANCELLED);

                        return activityRepository.save(activity);
                } else {
                        throw new EntityNotFoundException("Activity not found");
                }
        }
}
