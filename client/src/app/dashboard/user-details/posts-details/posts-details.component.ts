import { Post } from './../../posts/post.model';
import { Component, OnInit } from '@angular/core';
import { PostsService } from '../../posts/posts.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-posts-details',
  templateUrl: './posts-details.component.html',
  styleUrls: ['./posts-details.component.css']
})
export class PostsDetailsComponent implements OnInit {

  errorStatus: number;
  posts: Post[];
  constructor(private postService: PostsService, private router: Router) { }

  ngOnInit() {
    this.postService.getUserPosts().subscribe(
     res =>{
       this.posts = res;
     },
     err =>{
       this.errorStatus = err.status;}
    );
  }
  deletePost(forumPostId: string){
    console.log(forumPostId);
    if(confirm('Czy na pewno chcesz usunąć ten post?')){
      if(this.posts.length === 1){
        this.posts = [];
      }
      this.postService.deletePost(forumPostId).subscribe(
        res=>{
          console.log(res);
          this.ngOnInit();
        },
        err=>{
          console.log(err);
          this.errorStatus = err.status;
        }
      );
    }
  }

}
