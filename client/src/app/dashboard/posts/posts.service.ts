import { HttpClient } from '@angular/common/http';
import { Post } from './post.model';
import { Injectable } from '@angular/core';
@Injectable({
  providedIn: 'root'
})
export class PostsService {

  constructor(private http: HttpClient) { }
  
  getAllPosts(){
    return this.http.get<Post[]>('http://localhost:8080/post',{
      headers: {
      'Authorization' : localStorage.getItem('token'),
      'Content-Type' : 'application/json'
      }
    });
  }
  getUserPosts(){
    return this.http.get<Post[]>('http://localhost:8080/post/my',{
      headers: {
      'Authorization' : localStorage.getItem('token'),
      'Content-Type' : 'application/json'
      }
    });
  }

  addPost(category: string,title: string, postText: string){
    return this.http.post('http://localhost:8080/post',
    {
       title: title,
       category: category,
       postText: postText
    },{
      headers: {
      'Authorization' : localStorage.getItem('token'),
      'Content-Type' : 'application/json'
      }
    });
  }
  editPost(forumPostId: string, title: string, postText: string){
    return this.http.put('http://localhost:8080/post',
    {
       forumPostId: forumPostId,
       title: title,
       postText: postText
    },{
      headers: {
      'Authorization' : localStorage.getItem('token'),
      'Content-Type' : 'application/json'
      }
    });
  }
  ratePost(forumPostId: string, rate: string){
      return this.http.patch('http://localhost:8080/post/rate/'+forumPostId+'?rate='+rate,'',{
        responseType: 'text',
        headers: {
        'Authorization' : localStorage.getItem('token')
        }
      });
  }
  rateComment(commentId: string, rate: string){
    return this.http.patch('http://localhost:8080/comment/rate/'+commentId+'?rate='+rate,'',{
        responseType: 'text',
        headers: {
        'Authorization' : localStorage.getItem('token')
        }
      });
  }
  deletePost(forumPostId: string){
    return this.http.patch('http://localhost:8080/post/'+forumPostId,'',{
      headers: {
      'Authorization' : localStorage.getItem('token')
      }
    });
  }
  addComment(forumPostId: string, text: string){
    return this.http.post('http://localhost:8080/comment/'+forumPostId,{
      text: text
    },{
      headers: {
      'Authorization' : localStorage.getItem('token'),
      'Content-Type' : 'application/json'
      }
    });
  }
  deleteComment(commentId: string){
    return this.http.patch('http://localhost:8080/comment/'+commentId,'',{
      headers: {
      'Authorization' : localStorage.getItem('token')
      }
    });
  }
  editComment(commentId: string, text: string){
    return this.http.put('http://localhost:8080/comment/'+commentId,{
      text: text
    },{
      headers: {
      'Authorization' : localStorage.getItem('token'),
      'Content-Type' : 'application/json'
      }
    });
  }

}

