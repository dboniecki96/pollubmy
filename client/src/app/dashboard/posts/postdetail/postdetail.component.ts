import { Router } from '@angular/router';
import { DashboardService } from './../../dashboard.service';
import { NgForm } from '@angular/forms';
import { Comment } from './../comment.model';
import { Post } from './../post.model';
import { ActivatedRoute, Params } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { PostsService } from '../posts.service';

@Component({
  selector: 'app-postdetail',
  templateUrl: './postdetail.component.html',
  styleUrls: ['./postdetail.component.css']
})
export class PostdetailComponent implements OnInit {

  selectedCommentId: string;
  editable = false;
  userLogin: string;

  addPointStyle = 'black';
  subtractPointStyle = 'black';
  commentAddPointStyle = 'black';
  commentSubtractPointStyle = 'black';

  points: number;
  commentPoints: number;

  pointAdded: boolean;
  pointSubtracted: boolean;
  commentPointAdded: boolean;
  commentPointSubtracted: boolean;

  postId: string;
  selectedPost: Post;
  comments: Comment[];
  commentsLength: number;
  constructor(private route: ActivatedRoute, private postService: PostsService, private dashboardService: DashboardService, private router: Router) { }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.postId = params['id'];
          this.postService.getAllPosts().subscribe(
            res => {
              let filterPosts = res.filter(x => {
                return x.forumPostId === this.postId;
              });
              this.selectedPost = filterPosts[0];
              if (this.selectedPost.rate === 'plus') {
                this.addPointStyle = 'blue';
              }
              if (this.selectedPost.rate === 'minus') {
                this.subtractPointStyle = 'red';
              }
              if (this.selectedPost.rate === 'no') {
                this.subtractPointStyle = 'black';
                this.addPointStyle = 'black';
              }
              this.points = this.selectedPost.points;
              this.comments = this.selectedPost.commentsDTO;
              this.comments.filter(x => {
                if (x.owner && x.rate === 'plus') {
                  this.commentAddPointStyle = 'blue';
                }
                if (x.owner && x.rate === 'no') {
                  this.commentAddPointStyle = 'black';
                  this.commentSubtractPointStyle = 'black';
                }
                if (x.owner && x.rate === 'minus') {
                  this.commentSubtractPointStyle = 'red';
                }
              });
              this.commentsLength = this.selectedPost.commentsDTO.length;
              console.log(this.selectedPost);
              console.log(this.comments);
            }
          );
        }
      );
    this.dashboardService.getUser().subscribe(
      res => {
        this.userLogin = res.login;
      }
    );
  }
  addPostPoint() {
    this.addPoint(this.points);
  }
  subtractPostPoint() {
    this.subtractPoint(this.points);
  }
  addCommentPoint(commentId: string) {
    console.log(commentId);
    let commentPoints = this.comments.find(x => x.commentId === commentId).points;
    if (this.commentPointSubtracted) {
      if (commentPoints === this.points) {
        this.postService.rateComment(commentId, 'plus').subscribe(
          res => {
            commentPoints++;
            this.commentAddPointStyle = 'blue';
            this.commentSubtractPointStyle = 'black';
            console.log('Added' + this.pointAdded);
            this.ngOnInit();
          }, err => {
            console.log(err);
          }
        );
      }

    }
    if (this.commentPointAdded) {
      this.postService.rateComment(commentId, 'minus').subscribe(
        res => {
          commentPoints--;
          this.commentAddPointStyle = 'black';
          console.log('Added' + this.commentPointAdded);
          this.ngOnInit();
        }, err => {
          console.log(err);
        }
      );
    }
    else {
      this.postService.rateComment(commentId, 'plus').subscribe(
        res => {
          commentPoints++;
          this.commentAddPointStyle = 'blue';
          this.commentSubtractPointStyle = 'black';
          console.log('Added' + this.commentPointAdded);
          this.ngOnInit();
        }, err => {
          console.log(err);
        }
      );
    }
    this.ngOnInit();
  }
  subtractCommentPoint(commentId: string) {
    console.log(commentId);
    let commentPoints = this.comments.find(x => x.commentId === commentId).points;
    if (this.commentPointAdded) {
      this.postService.rateComment(commentId, 'minus').subscribe(
        res => {
          commentPoints--;
          this.commentSubtractPointStyle = 'red';
          this.commentAddPointStyle = 'black';
          this.ngOnInit();
        }, err => {
          console.log(err);
        }
      );

    }
    if (this.commentPointSubtracted) {
      this.postService.rateComment(commentId, 'plus').subscribe(
        res => {
          commentPoints++;
          this.commentSubtractPointStyle = 'black';
          this.ngOnInit();
        }, err => {
          console.log(err);
        }
      );
    }
    else {
      this.postService.rateComment(commentId, 'minus').subscribe(
        res => {
          commentPoints--;
          this.commentSubtractPointStyle = 'red';
          this.commentAddPointStyle = 'black';
          console.log('Subtract' + this.commentPointSubtracted);
          this.ngOnInit();
        }, err => {
          console.log(err);
        }
      );


    }
    this.ngOnInit();
  }

  addComment(comment: NgForm) {
    console.log(comment.value);
    const text = comment.value.text;
    console.log(text);
    this.postService.addComment(this.postId, text).subscribe(
      res => {
        console.log(res);
        this.ngOnInit();
        this.postService.getAllPosts();
      },
      err => {
        console.log(err);
      }
    );
  }
  deleteComment(commentId: string) {
    if (confirm('Czy na pewno chcesz usunąć ten komentarz?')) {
      this.postService.deleteComment(commentId).subscribe(
        res => {
          console.log(res);
          this.ngOnInit();
        },
        err => {
          console.log(err);
        }
      );
    }
  }
  allowEdit(commentId: string) {
    console.log(commentId);
    this.selectedCommentId = commentId;
    this.editable = true;
    console.log(this.editable);
  }
  cancelEdit(){
    this.editable = false;
    console.log(this.editable);
  }
  editComment(commentText: string) {
    console.log(commentText);
    this.postService.editComment(this.selectedCommentId, commentText).subscribe(
      res => {
        this.editable = false;
        this.ngOnInit();
      }, err => {
        console.log(err);
      }
    );
  }


  selectedSort(event) {
    const value = event.target.value;
    if (value === 'popular') {
      this.comments.sort((a, b) => b.points - a.points);
    }
    else if (value === 'old') {
      this.comments.sort((a, b) => (a.postTime > b.postTime) ? 1 : (b.postTime > a.postTime) ? -1 : 0);
    }
    else if (value === 'new') {
      this.comments.sort((a, b) => (b.postTime > a.postTime) ? 1 : (a.postTime > b.postTime) ? -1 : 0);
    }
  }


  addPoint(points: number) {
    if (this.pointSubtracted) {
      if (points === this.points) {
        this.postService.ratePost(this.postId, 'plus').subscribe(
          res => {
            points++;
            this.addPointStyle = 'blue';
            this.subtractPointStyle = 'black';
            console.log('Added' + this.pointAdded);
            this.ngOnInit();
          }, err => {
            console.log(err);
          }
        );
      }

    }
    if (this.pointAdded) {
      this.postService.ratePost(this.postId, 'minus').subscribe(
        res => {
          points--;
          this.addPointStyle = 'black';
          console.log('Added' + this.pointAdded);
          this.ngOnInit();
        }, err => {
          console.log(err);
        }
      );
    }
    else {
      this.postService.ratePost(this.postId, 'plus').subscribe(
        res => {
          points++;
          this.addPointStyle = 'blue';
          this.subtractPointStyle = 'black';
          console.log('Added' + this.pointAdded);
          this.ngOnInit();
        }, err => {
          console.log(err);
        }
      );
    }
    this.ngOnInit();
  }
  subtractPoint(points: number) {
    if (this.pointAdded) {
      this.postService.ratePost(this.postId, 'minus').subscribe(
        res => {
          points--;
          this.subtractPointStyle = 'red';
          this.addPointStyle = 'black';
          this.ngOnInit();
        }, err => {
          console.log(err);
        }
      );

    }
    if (this.pointSubtracted) {
      this.postService.ratePost(this.postId, 'plus').subscribe(
        res => {
          points++;
          this.subtractPointStyle = 'black';
          this.ngOnInit();
        }, err => {
          console.log(err);
        }
      );
    }
    else {
      this.postService.ratePost(this.postId, 'minus').subscribe(
        res => {
          points--;
          this.subtractPointStyle = 'red';
          this.addPointStyle = 'black';
          console.log('Subtract' + this.pointSubtracted);
          this.ngOnInit();
        }, err => {
          console.log(err);
        }
      );
    }
  }
 
}
