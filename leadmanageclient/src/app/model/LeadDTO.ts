import { UserDTO } from "./UserDTO";

export class LeadDTO {
    constructor(
        public quote: string = '',
        public budget: number = 0,
        public probability: string = '',
        public priority: string = '',
        public stage: string = '',
        public source: string = '',
        public tag: string = '',
        public expectedClosingDate?: Date,
        public assignedUser: UserDTO = new UserDTO()
    ) { }

    reset(): void {
        this.quote = '';
        this.budget = 0;
        this.probability = '';
        this.priority = '';
        this.stage = '';
        this.source = '';
        this.tag = '';
        this.expectedClosingDate = undefined;
        this.assignedUser = new UserDTO();
    }
}
