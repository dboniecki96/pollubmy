import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Privatelessons } from './privatelessons.model';
import { Observable } from 'rxjs';
import { Faculty } from '../faculty-models/faculty.model';
import { Component } from '@angular/core';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import {PrivatelessonsComponent} from './privatelessons.component';
import { FieldOfStudy } from '.././faculty-models/fieldOfStudy.model';


@Injectable({
  providedIn: 'root'
})
export class PrivatelessonsService {
  constructor(private http: HttpClient) { }

  getAllannouncements(): Observable<Privatelessons[]> {
    return this.http.get<Privatelessons[]>('http://localhost:8080/lesson/all', 
    {
      headers: {
        'Authorization' : localStorage.getItem('token'),
        'Content-Type' : 'application/json'
      }
    });

  }

  getMyannouncements(): Observable<Privatelessons[]> {
    return this.http.get<Privatelessons[]>('http://localhost:8080/lesson/my', 
    {
      headers: {
        'Authorization' : localStorage.getItem('token'),
        'Content-Type' : 'application/json'
      }
    });

  }
  getFaculties(): Observable<Faculty[]> {
    return this.http.get<Faculty[]>('http://localhost:8080/lesson', {
      headers: { 'Authorization': localStorage.getItem('token') }
    })
  }


  addPrivatelesson(time: number, price: number, description: string, faculty: string, fieldOfStudy: string, course: string) {
    return this.http.post('http://localhost:8080/lesson',
      {
        time: time,
        price: price,
        description: description,
        faculty: faculty,
        fieldOfStudy: fieldOfStudy,
        course: course
      }, {
        headers: {
          'Authorization': localStorage.getItem('token'),
          'Content-Type': 'application/json'
        }
      });
  }
  addPrivatelessonAsStudent(description: string, faculty: string, fieldOfStudy: string, course: string) {
    return this.http.post('http://localhost:8080//help_lesson',
      {
        description: description,
        faculty: faculty,
        fieldOfStudy: fieldOfStudy,
        course: course
      }, {
        headers: {
          'Authorization': localStorage.getItem('token'),
          'Content-Type': 'application/json'
        }
      });
  }
}
