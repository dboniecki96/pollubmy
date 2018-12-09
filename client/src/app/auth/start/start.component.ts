import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {

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
