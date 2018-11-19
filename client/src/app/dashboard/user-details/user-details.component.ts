import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  
  constructor() { }

  ngOnInit() {
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
    event.target.className += " active";
  }
}
