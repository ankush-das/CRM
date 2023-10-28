import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LeadDTO } from 'src/app/model/LeadDTO';
import { LeadFormService } from 'src/app/services/LeadFormService';

@Component({
  selector: 'app-leadformpage',
  templateUrl: './leadformpage.component.html',
  styleUrls: ['./leadformpage.component.css']
})
export class LeadformpageComponent {

  users: any[] = [];
  lead: LeadDTO = new LeadDTO();
  leadID: number = 0;
  isEditMode = false;
  successMessage: string = '';

  constructor(private leadFormService: LeadFormService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.leadFormService.getUsers().subscribe(data => {
      this.users = data;
    });

    this.route.params
      .subscribe(params => {
        this.leadID = +params['leadID']; // Use '+' to convert the parameter to a number

      });
  }

  onSubmit() {

    this.leadFormService.createLead(this.leadID, this.lead).subscribe({
      next: (response) => {
        console.log('Data sent successfully:', this.leadID);
        this.successMessage = 'Lead captured successfully';
        this.lead.reset();
        setTimeout(() => {
          this.successMessage = '';
        }, 3000);
      },
      error: (error) => {
        console.error('Error:', error);
      }
    });
  }

  updateLead(leadId: number, leadDTO: any) {
    this.leadFormService.updateLead(leadId, leadDTO).subscribe({
      next: (updatedLead) => {
        console.log('Lead updated successfully:', updatedLead);
      },
      error: (error) => {
        console.error('Update failed:', error);
      }
    });
  }

  patchSoloLead(leadId: number, leadDTO: any) {
    this.leadFormService.patchLead(leadId, leadDTO).subscribe({
      next: (updatedLead) => {
        console.log('Lead updated successfully:', updatedLead);
      },
      error: (error) => {
        console.error('Error:', error);
      }
    });
  }
}
