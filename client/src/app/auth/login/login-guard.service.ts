import { LoginService } from './login.service';
import { CanActivate, Router } from "@angular/router";
import { Injectable } from "@angular/core";
@Injectable()

export class LoginGuard implements CanActivate {

  constructor(private loginService: LoginService, private router: Router){}
  canActivate(){ 
    if(!this.loginService.isLoggedIn()){
        this.router.navigate(['./login']);
    }
    else{
        return this.loginService.isLoggedIn();
    } 
}}



