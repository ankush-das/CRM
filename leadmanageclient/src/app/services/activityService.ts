import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LeadActivity } from '../model/LeadActivity';

@Injectable({
    providedIn: 'root'
})
export class ActivityService {
    private baseUrl = 'http://localhost:8080/api/activities'; // Replace with your API URL

    constructor(private http: HttpClient) { }

    getAllLogs(capturedLeadId: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/log/${capturedLeadId}`);
    }

    createActivity(capturedLeadId: number, activityDTO: any): Observable<LeadActivity> {
        const url = `${this.baseUrl}/create/${capturedLeadId}`;
        return this.http.post<LeadActivity>(url, activityDTO);
    }

    getAllActivities(): Observable<LeadActivity[]> {
        const url = `${this.baseUrl}/all`;
        return this.http.get<LeadActivity[]>(url);
    }

    updateActivityCompleteStatus(capturedLeadId: number): Observable<LeadActivity> {
        const url = `${this.baseUrl}/update/status/complete/${capturedLeadId}`;
        return this.http.put<LeadActivity>(url, {});
    }

    updateActivityCancleStatus(capturedLeadId: number): Observable<LeadActivity> {
        const url = `${this.baseUrl}/update/status/cancel/${capturedLeadId}`;
        return this.http.put<LeadActivity>(url, {});
    }
}
