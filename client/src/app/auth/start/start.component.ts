import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {

  isRegisterButtonClicked: boolean;
  isLoginLinkClicked: boolean;
  constructor() { }

  ngOnInit() {
    this.isRegisterButtonClicked = false;
    this.isLoginLinkClicked = false;

  }
  onClickRegisterButton(){
    this.isRegisterButtonClicked = true;
    localStorage.setItem('canRegister', this.isRegisterButtonClicked.toString());
    return localStorage.getItem('canRegister') == this.isRegisterButtonClicked.toString();
  }
  onClickLoginButton(){
    this.isLoginLinkClicked = true;
    localStorage.setItem('canLogin', this.isLoginLinkClicked.toString());
    return localStorage.getItem('canLogin') == this.isLoginLinkClicked.toString();
  }


}
