import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {

  isRegisterButtonClicked = false;
  isLoginLinkClicked = false
  constructor() { }

  ngOnInit() {
    }
  onClickRegisterButton(){
    this.isRegisterButtonClicked = true;
  }
  onClickLoginButton(){
    this.isLoginLinkClicked = true;
  }

}
