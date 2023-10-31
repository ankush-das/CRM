import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ContactInfo } from 'src/app/model/ContactInfo';
import { LeadFormService } from 'src/app/services/LeadFormService';

@Component({
  selector: 'app-contactform',
  templateUrl: './contactform.component.html',
  styleUrls: ['./contactform.component.css']
})
export class ContactformComponent {
  contactInfo: ContactInfo = new ContactInfo();
  successMessage: string = '';
  leadID: number = 0;

  constructor(private leadFormService: LeadFormService, private route: ActivatedRoute) {
    this.route.params
      .subscribe(params => {
        this.leadID = +params['leadID'];
      });
  }

  // onSubmit(contactInfoForm: NgForm) {
  //   if (contactInfoForm.valid) {
  //     this.leadFormService.createLeadContact(this.leadID, this.contactInfo)
  //       .subscribe((createdContact: ContactInfo) => {
  //         console.log('Lead contact created:', createdContact);
  //         window.location.reload();
  //       }, error => {
  //         console.error('Error creating lead contact:', error);
  //       });
  //   } else {
  //     console.log('Form is invalid. Please fill in all required fields.');
  //   }
  // }

  onSubmit(contactInfoForm: NgForm) {
    if (contactInfoForm.valid) {
      this.leadFormService.createLeadContact(this.leadID, this.contactInfo)
        .subscribe({
          next: (createdContact: ContactInfo) => {
            console.log('Lead contact created:', createdContact);
            // window.location.reload();

            this.successMessage = 'Form submitted successfully';
            this.contactInfo.reset();
            setTimeout(() => {
              this.successMessage = '';
            }, 3000);

          },
          error: (error) => {
            console.error('Error creating lead contact:', error);
          }
        });
    } else {
      console.log('Form is invalid. Please fill in all required fields.');
    }
  }
}
