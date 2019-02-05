export class Comment{
    constructor(
        public commentId: string,
        public owner: boolean,
        public points: number,
        public postTime: string,
        public text: string,
        public userLogin: string,
        public rate: string
    ){}
}