export class FileModel {
    constructor(
        public fileInformationId: string,
        public faculty: string,
        public fieldOfStudy: string,
        public course: string,
        public description: string,
        public fileName: string,
        public owner: boolean,
        public uploadFileDate: string
    ) {}
}


