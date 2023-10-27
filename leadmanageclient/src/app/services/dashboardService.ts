import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DashboardDTO } from '../model/DashboardDTO';

@Injectable({
    providedIn: 'root'
})
export class DashboardService {
    private baseUrl = 'http://localhost:8080/api/dash'; // Replace with your actual API URL

    constructor(private http: HttpClient) { }

    getDashboardData(): Observable<DashboardDTO> {
        return this.http.get<DashboardDTO>(`${this.baseUrl}/info`);
    }

    getLeadCountInEachStage() {
        const url = `${this.baseUrl}/lead/stage`;
        return this.http.get<Map<string, number>>(url);
    }

    getRevenueByStage() {
        const url = `${this.baseUrl}/revenue`;
        return this.http.get<Map<string, number>>(url);
    }

    getExpectedRevenueByStage() {
        const url = `${this.baseUrl}/expectedrevenue`;
        return this.http.get<Map<string, number>>(url);
    }
}
