import { Component, OnInit, Input } from '@angular/core';
import { Post } from './post.model';
import { Router, ActivatedRoute } from '@angular/router';
import { PostsService } from './posts.service';
import { e } from '@angular/core/src/render3';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {
  
  categories: string[] = [
    'Wolny temat','Wydarzenia','Nauka','Materiały'
  ];
  errorStatus: number;
  posts: Post[];
  
  constructor(public route: ActivatedRoute, private postService: PostsService) { }

  ngOnInit() {
    this.postService.getAllPosts().subscribe(
      res=>{
        this.posts = res; 
        console.log(this.posts);
      },
      err=>{
        console.log(err);
        this.errorStatus = err.status;
      }
    )
  }

  selectPost(event: any){
    console.log(event);
  }
  selectedSort(event){
    const value = event.target.value;
    if(value === 'popular'){
      this.posts.sort((a,b)=> b.points - a.points);
    }
    else if(value === 'old'){
      this.posts.sort((a,b)=> (a.addPostTime > b.addPostTime) ? 1 : (b.addPostTime > a.addPostTime) ? -1 : 0);
    }
    else if(value === 'new'){
      this.posts.sort((a,b)=> (b.addPostTime > a.addPostTime) ? 1 : (a.addPostTime > b.addPostTime) ? -1 : 0);
    }
  }
  selectedCategory(event){
    const value = event.target.value.toString();
    console.log(value);
    let categoryPostList = this.posts.filter(x=>x.category === value);
    console.log(categoryPostList);
 }

}
