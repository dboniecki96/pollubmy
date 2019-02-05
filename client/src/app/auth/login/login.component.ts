import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import { LoginService } from './login.service';
import { User } from '../user.model';
import {map} from 'rxjs/operators';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isLoggedIn = false;
  constructor(public loginService: LoginService, private router: Router) { }

  ngOnInit() {}


  onLogin(f: NgForm){
    const loginOrEmail = f.value.loginOrEmail;
    const password = f.value.password;
    this.loginService.logInUser(loginOrEmail,password);  
  }
}
