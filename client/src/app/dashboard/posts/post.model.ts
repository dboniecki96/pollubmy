import { Comment } from './comment.model';

export class Post{
    constructor(
        public forumPostId: string,
        public commentsDTO: Comment[],
        public category: string,
        public addPostTime: string,
        public points: number,
        public postText: string,
        public title: string,
        public userLogin: string,
        public rate: string
    ){}
}