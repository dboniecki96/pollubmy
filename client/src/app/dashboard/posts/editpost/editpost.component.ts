import { Post } from './../post.model';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { PostsService } from '../posts.service';
import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-editpost',
  templateUrl: './editpost.component.html',
  styleUrls: ['./editpost.component.css']
})
export class EditpostComponent implements OnInit {

  selectedPostId: string;
  selectedPost: Post;
  title: string;
  text: string;
  constructor(private postService: PostsService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.route.params
    .subscribe(
      (params: Params)=>{
         this.selectedPostId = params['id'];
         this.postService.getAllPosts().subscribe(
          res=>{
            let filterPosts = res.filter(x=>{
              return x.forumPostId === this.selectedPostId;
            }); 
            this.selectedPost = filterPosts[0];
            this.title = this.selectedPost.title;
            this.text = this.selectedPost.postText
            console.log(this.selectedPost);
          }
         );
      }
    );  
  }
  editPost(edit: NgForm){
    const value = edit.value;
    console.log(value);
    if(confirm('Czy na pewno chcesz zedytować ten post?')){
      this.postService.editPost(this.selectedPostId,value.title,value.text).subscribe(
        res=>{
          this.router.navigate(['/dashboard/posts/'+this.selectedPostId]);
        }, err=>{
          console.log(err);
        }
      );
    }
  }
  return(){
    this.router.navigate(['/dashboard/posts']);
    this.postService.getAllPosts();
  }
}

