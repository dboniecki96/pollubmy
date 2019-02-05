import { Component, OnInit } from '@angular/core';
import { PrivatelessonsService } from './privatelessons.service';
import {Privatelessons} from './privatelessons.model';
@Component({
  selector: 'app-privatelessons',
  templateUrl: './privatelessons.component.html',
  styleUrls: ['./privatelessons.component.css']
})
export class PrivatelessonsComponent implements OnInit {
  announcements: Privatelessons[];

  constructor(private lessonService: PrivatelessonsService) { }

  ngOnInit() {
    this.lessonService.getAllannouncements().subscribe(
      response => {
        this.announcements = response;
        console.log(response)

      },
      error=> {
        console.log(error);

      }
    )
  }
}
