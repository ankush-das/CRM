import { Component, OnInit } from '@angular/core';
import { LeadDTO } from 'src/app/model/LeadDTO';
import { PipelineService } from 'src/app/services/PipelineService';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {
  leads: any[] = [];


  constructor(private pipelineService: PipelineService) { }

  ngOnInit() {
    this.loadLeads();
  }

  loadLeads() {
    this.pipelineService.getAllLeads().subscribe(
      (data) => {
        this.leads = data.filter((lead: any) => lead.stage === "CLOSED_WON");
        console.log(this.leads);
      },
      (error) => {
        console.error('Error fetching leads: ', error);
        // You can also set an error flag or message here for the user
      }
    );
  }
}
