import { PrivatelessonsService } from './../../privatelessons/privatelessons.service';
import { Privatelessons } from './../../privatelessons/privatelessons.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-lessons-details',
  templateUrl: './lessons-details.component.html',
  styleUrls: ['./lessons-details.component.css']
})
export class LessonsDetailsComponent implements OnInit {

  noLessonsErrorStatus: number;
  announcements: Privatelessons[] = [
  ];
  constructor(private privateLessonService: PrivatelessonsService) { }

  ngOnInit() {
    this.privateLessonService.getMyannouncements().subscribe(
      res=>{
        this.announcements = res;
        console.log(res);
      },
      err=>{
        this.noLessonsErrorStatus = err.status;
        console.log(this.noLessonsErrorStatus);
      }
    );
  }

}
