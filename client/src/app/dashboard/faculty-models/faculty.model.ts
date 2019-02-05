import { FieldOfStudy } from './fieldOfStudy.model';
export class Faculty{
    constructor(
        public faculty: string,
        public fieldOfStudies: FieldOfStudy[]
    ){}
}