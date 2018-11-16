import { LoginService } from './login.service';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from "@angular/router";
import { Injectable } from "@angular/core";
@Injectable()

export class LoginGuard implements CanActivate {

  constructor(private loginService: LoginService, private router: Router){}
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot){ 
    if(!this.loginService.isLoggedIn()){
        this.router.navigate(['./login']);
        console.log('Not authenticated');
    }
    else{
        return this.loginService.isLoggedIn();
    } 
}}