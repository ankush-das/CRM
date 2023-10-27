import { Component, OnInit } from '@angular/core';
import { DashboardDTO } from 'src/app/model/DashboardDTO';
import { ActivityService } from 'src/app/services/activityService';
import { DashboardService } from 'src/app/services/dashboardService';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  dashboardData: DashboardDTO = new DashboardDTO();
  activities: any;

  constructor(private dashboardService: DashboardService, private activityService: ActivityService) { }

  ngOnInit(): void {
    this.dashboardService.getDashboardData().subscribe(data => {
      this.dashboardData = data;
    });
    this.getActivities();
  }

  getActivities(): void {
    this.activityService.getAllActivities()
      .subscribe(data => {
        this.activities = data;
      },
        error => {
          console.error('Error fetching activities:', error);
        });
  }

  updateStatus(capturedLeadId: number) {
    this.activityService.updateActivityCompleteStatus(capturedLeadId).subscribe(
      updatedActivity => {
        window.location.reload();
        console.log('Activity status updated:', updatedActivity);
      },
      error => {
        console.error('Error updating activity status:', error);
      }
    );
  }

  updateStatuscancel(capturedLeadId: number) {
    this.activityService.updateActivityCancleStatus(capturedLeadId).subscribe(
      updatedActivity => {
        window.location.reload();
        console.log('Activity status updated:', updatedActivity);
      },
      error => {
        console.error('Error updating activity status:', error);
      }
    );
  }
}
