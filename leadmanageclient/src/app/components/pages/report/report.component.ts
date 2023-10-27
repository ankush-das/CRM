import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ChartDataset, ChartOptions, ChartType } from 'chart.js';
import { PipelineService } from 'src/app/services/PipelineService';
import { DashboardService } from 'src/app/services/dashboardService';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {
  public barChartOptions: ChartOptions = {
    responsive: true,
    maintainAspectRatio: false,
    scales: {
      x: {
        display: true,
      },
      y: {
        display: true,
      },
    },
  };
  public barChartType: ChartType = 'bar';
  public barChartLegend = true;
  barChartData: any[] = [];
  barChartLabels: string[] = [];

  revenueByStage: Map<string, number> = new Map();
  expectedRevenueByStage: Map<string, number> = new Map();
  lineChartData: any[] = [];
  lineChartLabels: string[] = [];
  lineChartOptions: any = {
    responsive: true,
  };
  lineChartLegend = true;
  lineChartType = 'line';


  public pieChartOptions: ChartOptions = {
    responsive: true,
  };


  public pieChartData: ChartDataset[] = [];
  public pieChartLabels: string[] = [];

  public pieChartType: ChartType = 'pie';
  public pieChartLegend = true;

  constructor(private pipelineService: PipelineService, private dashboardService: DashboardService, private http: HttpClient) { }

  ngOnInit() {
    this.dashboardService.getLeadCountInEachStage().subscribe(data => {
      this.barChartLabels = Object.keys(data);
      this.barChartData[0] = { data: Object.values(data), label: 'Lead Counts' };
    });

    this.http.get<number>('http://localhost:8080/api/dash/new').subscribe((newLeadCount: number) => {
      this.pieChartLabels = Object.keys(newLeadCount);
      this.pieChartData[0] = { data: Object.values(newLeadCount), label: 'new-count' }
    });

    this.http.get<number>('http://localhost:8080/api/dash/won-closed').subscribe((closedWonCount: number) => {
      this.pieChartLabels = Object.keys(closedWonCount);
      this.pieChartData[1] = { data: Object.values(closedWonCount), label: 'won-closed' }
    });

    this.http.get<number>('http://localhost:8080/api/dash/lost-closed').subscribe((closedLostCount: number) => {
      this.pieChartLabels = Object.keys(closedLostCount);
      this.pieChartData[2] = { data: Object.values(closedLostCount), label: 'lost-closed' }
    });
    this.combineDataAndSetChart();
  }

  combineDataAndSetChart() {
    this.dashboardService.getRevenueByStage().subscribe((revenueData) => {
      const revenueLabels = Object.keys(revenueData);
      const revenueValues = Object.values(revenueData);

      this.dashboardService.getExpectedRevenueByStage().subscribe((expectedRevenueData) => {
        const expectedRevenueLabels = Object.keys(expectedRevenueData);
        const expectedRevenueValues = Object.values(expectedRevenueData);

        // Assuming both revenue and expected revenue data have the same labels
        this.lineChartLabels = revenueLabels;
        this.lineChartData = [
          { data: revenueValues, label: 'Lead Revenue' },
          { data: expectedRevenueValues, label: 'Lead Expected Revenue' }
        ];
      });
    });
  }

}