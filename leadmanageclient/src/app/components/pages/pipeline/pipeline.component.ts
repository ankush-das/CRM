import { Component, OnInit, ViewChild } from '@angular/core';
import { PipelineService } from 'src/app/services/PipelineService';
import { faLeftLong, faRightLong, faSearch, faStar, faClock } from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';
import { IconProp } from '@fortawesome/fontawesome-svg-core';
import { ActivityService } from 'src/app/services/activityService';
import { LeadActivity } from 'src/app/model/LeadActivity';
import { LeadFormService } from 'src/app/services/LeadFormService';


@Component({
  selector: 'app-pipeline',
  templateUrl: './pipeline.component.html',
  styleUrls: ['./pipeline.component.css']
})
export class PipelineComponent implements OnInit {
  faRightLong = faRightLong;
  faLeftLong = faLeftLong;
  faClock = faClock
  faStar = faStar;
  faSearch = faSearch;
  leads: any[] = [];
  searchTerm: string = '';
  capturedLeadId: number = 0;
  users: any[] = [];
  successMessage: string = '';
  activityDTO: LeadActivity = new LeadActivity();

  constructor(private pipelineService: PipelineService, private leadFormService: LeadFormService, private activityService: ActivityService, private router: Router) { }

  ngOnInit() {
    this.pipelineService.getAllLeads().subscribe((leads) => {
      this.leads = leads;
      console.log(this.leads);
    });

    this.leadFormService.getUsers().subscribe(data => {
      this.users = data;
    });
    console.log(this.searchTerm);

  }

  @ViewChild('successModal') successModal: any;

  onTransitionNext(event: Event, leadId: number) {
    event.stopPropagation();
    this.pipelineService.nextLeadStageTransition(leadId).subscribe({
      next: (response) => {
        console.log('Transition successful:', response);
        window.location.reload();
      },
      error: (error) => {
        console.error('Transition failed:', error);
      },
    });
  }

  onTransitionPrev(event: Event, leadId: number) {
    event.stopPropagation();
    this.pipelineService.prevLeadStageTransition(leadId).subscribe({
      next: (response) => {
        console.log('Previous transition successful:', response);
        window.location.reload();
      },
      error: (error) => {
        console.error('Previous transition failed:', error);
      },
    });
  }


  searchLeadsBySource() {
    this.pipelineService.searchLeadsByCompanyName(this.searchTerm)
      .subscribe(
        (leads) => {
          this.leads = leads;
          console.log(this.leads);

        },
        (error) => {
          console.error('Error searching by source:', error);
        }
      );
  }

  // searchLeadsBySource() {
  //   this.pipelineService.filterLeadsBySource(this.searchTerm).subscribe({
  //     next: (leads) => {
  //       this.leads = leads;
  //     },
  //     error: (error) => {
  //       console.error('Error searching by source:', error);
  //     },
  //   });
  // }


  generateStars(priority: string): { icon: IconProp; starClass: string }[] {
    const stars: { icon: IconProp; starClass: string }[] = [];
    switch (priority) {
      case 'HIGH':
        for (let i = 0; i < 3; i++) {
          stars.push({ icon: faStar, starClass: 'star-gold' });
        }
        break;
      case 'MEDIUM':
        for (let i = 0; i < 2; i++) {
          stars.push({ icon: faStar, starClass: 'star-gold' });
        }
        break;
      case 'LOW':
        stars.push({ icon: faStar, starClass: 'star-gold' });
        break;
      default:
        break;
    }
    return stars;
  }

  onactivityCreation(event: Event, leadId: number) {
    event.stopPropagation();
    this.capturedLeadId = leadId;
  }

  createActivity(capturedLeadId: number, activityDTO: LeadActivity) {
    this.activityService.createActivity(capturedLeadId, activityDTO).subscribe({
      next: (createdActivity) => {
        console.log('Activity created:', createdActivity);
        this.successMessage = 'Activity scheduled successfully';
        activityDTO.reset();
        setTimeout(() => {
          this.successMessage = '';
        }, 3000);
      },
      error: (error) => {
        // Handle errors here
        console.error('Error creating activity:', error);
      },
    });
  }
}
