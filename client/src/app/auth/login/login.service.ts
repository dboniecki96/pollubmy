import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  token: string;
  errorStatus: number;
  constructor(private http: HttpClient, private router: Router) { }

  logInUser(loginOrEmail: string, password: string){ 
    const options = {responseType : 'text' as 'json'};
    return this.http.post<any>('http://localhost:8080/login',{
      loginOrEmail : loginOrEmail,
      password: password
    },{observe: 'response'}
    ).subscribe(
      (res)=>{
        console.log(res);
        this.token = res.headers.get("Authorization");
        localStorage.setItem('token', this.token);
        // localStorage.setItem('token',this.tempToken);
        this.router.navigate(['./dashboard']);
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
  logoutUser(){
    if(localStorage.getItem('token') != null){
      localStorage.removeItem('token');
      this.router.navigate(['/login']);
      console.log('Successfully logged out')
    }
  }
}
