import { FileUploadService } from './file-upload/file-upload.service';
import { PostsService } from './posts/posts.service';
import { Post } from './posts/post.model';
import { FileModel } from './file-upload/file.model';
import { Component, OnInit } from '@angular/core';
import { DashboardService } from './dashboard.service';
import { UserDetails } from './user-models/user-details';
import { User } from './user-models/user-model';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  files: FileModel[] = [];
  posts: Post[] = [];

  public userDetails: User;
  public firstName: string;

  noPostsErrorStatus: number;
  noFilesErrorStatus: number;

  constructor(private dashboardService: DashboardService, private postService: PostsService, private fileService: FileUploadService) { }

  ngOnInit() {
    this.dashboardService.getUser().subscribe(
      res=>{
        console.log(res);
        console.log(res.firstName);
      },
      err=>{
        console.log(err);
      }
    );
    this.postService.getAllPosts().subscribe(
      res=> this.posts = res,
      err=> this.noPostsErrorStatus = err.status
    );
    this.fileService.getAllFiles().subscribe(
      res=> this.files = res,
      err=> this.noFilesErrorStatus = err.status
    );
  }
  downloadFile(fileInformationId: string, fileName: string){
    this.fileService.downloadFile(fileInformationId).subscribe(
      res=>{
        let url = window.URL.createObjectURL(res);
        let a = document.createElement("a");
        a.download = fileName;
        a.href = url;
        a.click();
      },
      err=>console.log(err)
    )
  }
  deleteFile(fileInformationId: string){
    if(confirm('Czy na pewno chcesz usunąć ten plik?')){
      if(this.files.length === 1){
        this.files = [];
      }
      this.fileService.deleteFile(fileInformationId).subscribe(
        res=>{
          this.ngOnInit();
          console.log(res);
        },err=>{
          console.log(err);
          
        },
      );
    }
  }

  filterPosts(){
    return this.posts.sort((a,b)=> (b.addPostTime > a.addPostTime) ? 1 : (a.addPostTime > b.addPostTime) ? -1 : 0);
  }
  filterFiles(){
    return this.files.sort((a,b)=> (b.uploadFileDate > a.uploadFileDate) ? 1 : (a.uploadFileDate > b.uploadFileDate) ? -1 : 0);
  }



}
