import { PostsService } from './../posts.service';
import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-addpost',
  templateUrl: './addpost.component.html',
  styleUrls: ['./addpost.component.css']
})
export class AddpostComponent implements OnInit {

  categories: string[] = [
    'Wolny temat','Wydarzenia','Nauka','Materiały'
  ];
  constructor(private postService: PostsService, private router: Router) { }

  ngOnInit() {
  }
  addPost(post: NgForm){
    const value = post.value;
    console.log(value);
    if(confirm('Czy na pewno chcesz opublikować post?')){
      this.postService.addPost(value.category,value.title,value.text).subscribe(
        res=>{
          console.log(res);
          this.router.navigate(['/dashboard/posts']);
          this.postService.getAllPosts();
        },
        err=>console.log(err)
      );
    }
  }
}
