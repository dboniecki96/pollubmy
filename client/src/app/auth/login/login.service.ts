import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { DashboardService } from '../../dashboard/dashboard.service';
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  
  constructor(private http: HttpClient, private router: Router) { }
  
  token: string;
  errorStatus: number;
  logInUser(loginOrEmail: string, password: string){ 
    return this.http.post<any>('http://localhost:8080/login',{
      loginOrEmail : loginOrEmail,
      password: password
    },{observe: 'response'}
    ).subscribe(
      (res)=>{
        console.log(res);
        this.token = res.headers.get("Authorization");
        localStorage.setItem('token', this.token);
        this.router.navigate(['./dashboard/details']);
        this.isLoggedIn();
      },
      (err)=>{
        this.errorStatus = err.status;
        this.getErrorStatus();
        console.log(err);
      });
  }
  getErrorStatus(){
    return this.errorStatus;
  }
  isLoggedIn(){
      return localStorage.getItem('token') != null;
  }
  isLoggedOut(){
      return localStorage.getItem('token') === null;
  }
  logoutUser(){
    if(localStorage.getItem('token') != null){
      localStorage.removeItem('token');
      this.router.navigate(['/login']);
      console.log('Successfully logged out');
      this.isLoggedOut();
    }
  }
}
