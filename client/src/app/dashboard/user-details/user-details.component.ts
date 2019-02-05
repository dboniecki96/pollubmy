import { DashboardService } from './../dashboard.service';
import { Component, OnInit, ViewChild } from '@angular/core';
@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
  @ViewChild('profileDetails') profileDetails;
  @ViewChild('lessonsDetails') lessonsDetails;
  @ViewChild('postsDetails') postsDetails;
  @ViewChild('filesDetails') filesDetails;

  constructor() { }

  ngOnInit() {
    this.profileDetails.nativeElement.style.display = 'block';
    this.lessonsDetails.nativeElement.style.display = 'none';
    this.postsDetails.nativeElement.style.display = 'none';
    this.filesDetails.nativeElement.style.display = 'none';
  
  }

  selectedDetail(event: any, detailName: string){
    let tab,links;
    tab = document.getElementsByClassName("tab");
    for(let i=0;i<tab.length;i++){
      tab[i].style.display = "none";
    }
    links = document.getElementsByClassName("link");
    for(let i=0;i<links.length;i++){
      links[i].className = links[i].className.replace(" active","");
    }
    document.getElementById(detailName).style.display="block";
  }
}
