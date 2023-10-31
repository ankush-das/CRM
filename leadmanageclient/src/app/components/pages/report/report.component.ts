import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ChartDataset, ChartOptions, ChartType } from 'chart.js';
import { forkJoin } from 'rxjs';
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

  newLeadC: number = 0;
  closedWonCount: number = 0;
  closedLostCount: number = 0;

  pieChartData: any = [
    {
      data: [1, 1, 1],
      backgroundColor: ['#FF5733', '#33FF57', '#5733FF'],
      label: 'Sample Data',
    },
  ];

  pieChartOptions: any = {
    responsive: true,
  };


  pieChartLabels: string[] = [];

  pieChartType = 'pie';
  pieChartLegend = true;

  constructor(private dashboardService: DashboardService, private http: HttpClient) { }

  ngOnInit() {
    this.dashboardService.getLeadCountInEachStage().subscribe(data => {
      this.barChartLabels = Object.keys(data);
      this.barChartData[0] = { data: Object.values(data), label: 'Lead Counts' };
    });

    const newLeadCount$ = this.http.get<number>('http://localhost:8080/api/dash/new');
    const closedWonCount$ = this.http.get<number>('http://localhost:8080/api/dash/won-closed');
    const closedLostCount$ = this.http.get<number>('http://localhost:8080/api/dash/lost-closed');

    forkJoin([newLeadCount$, closedWonCount$, closedLostCount$]).subscribe(
      ([newLeadCount, closedWonCount, closedLostCount]) => {
        const resultArray = [newLeadCount, closedWonCount, closedLostCount];

        this.pieChartData[0].data = resultArray;
        console.log(this.pieChartData[0].data);
      }
    );

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


// this.http.get<number>('http://localhost:8080/api/dash/new').subscribe((newLeadCount: number) => {
//   this.newLeadC = newLeadCount;
//   console.log(this.newLeadC);
// });

// this.http.get<number>('http://localhost:8080/api/dash/won-closed').subscribe((closedWonCount: number) => {
//   this.closedWonCount = closedWonCount;
// });

// this.http.get<number>('http://localhost:8080/api/dash/lost-closed').subscribe((closedLostCount: number) => {
//   this.closedLostCount = closedLostCount;
// });