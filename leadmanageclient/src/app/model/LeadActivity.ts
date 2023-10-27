export class LeadActivity {
    activityType: string;
    activityStatus: string;
    dueDate: Date;
    summary: string;
    detail: string;
    assignedUser: number;

    constructor() {
        this.activityType = '';
        this.activityStatus = '';
        this.dueDate = new Date();
        this.summary = '';
        this.detail = '';
        this.assignedUser = 0;
    }
}
