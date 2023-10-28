export class ContactInfo {
    id: number = 0;
    companyName: string = '';
    jobPosition: string = '';
    address: string = '';
    city: string = '';
    state: string = '';
    postalCode: string = '';
    region: string = '';

    reset(): void {
        this.id = 0;
        this.companyName = '';
        this.jobPosition = '';
        this.address = '';
        this.city = '';
        this.state = '';
        this.postalCode = '';
        this.region = '';
    }
}
