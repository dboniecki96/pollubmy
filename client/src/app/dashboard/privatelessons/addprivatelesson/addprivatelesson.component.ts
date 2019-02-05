import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { PrivatelessonsService} from './../privatelessons.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-addprivatelesson',
  templateUrl: './addprivatelesson.component.html',
  styleUrls: ['./addprivatelesson.component.css']
})
export class AddprivatelessonComponent implements OnInit {

  constructor(private PrivatelessonsService: PrivatelessonsService, private router: Router) { }

  ngOnInit() {
  }
 

}
