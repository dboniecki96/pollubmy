import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user-models/user-model';
import {Location} from '@angular/common';
@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  errorMessage = 'Niepoprawne stare hasło';
  url = 'http://localhost:8080/user';
  constructor(private http: HttpClient, public location: Location) { }

  getUser(): Observable<User>{
    return this.http.get<User>(this.url,{
      headers: {'Authorization' : localStorage.getItem('token')}
    });
  }
  editUser(user: User){
    return this.http.put<User>(this.url,user,{
      headers: {'Authorization' : localStorage.getItem('token')}
    })
  }
  
  changePassword(newPassword: string){
    return this.http.patch(this.url +'/updatePassword',newPassword,{
      headers: {
      'Authorization' : localStorage.getItem('token'),
      'Content-Type' : 'application/json'
      }
    });
  }
  public backClicked(){
    this.location.back()
  }
}
