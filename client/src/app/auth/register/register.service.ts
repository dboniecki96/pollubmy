import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { map, retry } from 'rxjs/operators';
import { User } from './user.model';
@Injectable()
export class RegisterService {
    errorStatus: number;
    constructor(private http: Http) { }
    storeUsers(user: User) {
        return this.http.post('http://localhost:8080/register', user).pipe(map(res => res.json()));
    }
}
