import { Faculty } from './../faculty-models/faculty.model';
import { Observable } from 'rxjs';
import { FileModel } from './file.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  headers = new HttpHeaders({
    'Authorization': localStorage.getItem('token')
  });
  constructor(private http: HttpClient) { }

  getFaculties(): Observable<Faculty[]>{
    return this.http.get<Faculty[]>('http://localhost:8080/lesson',{
      headers: this.headers
    })
  }
  uploadFile(formdata: FormData) {
    return this.http.post('http://localhost:8080/uploadFile', formdata, {
      headers: this.headers,
      reportProgress: true,
      observe: 'events'
    });
  }
  downloadFile(fileInformationId: string) {
    return this.http.get('http://localhost:8080/download/' + fileInformationId, {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': localStorage.getItem('token')
      },
      responseType: 'blob'
    })
  }
  getAllFiles() {
    return this.http.get<FileModel[]>('http://localhost:8080/uploadFile/all', {
      headers: this.headers
    })
  }
  
  getUserFiles() {
    return this.http.get<FileModel[]>('http://localhost:8080/uploadFile/my', {
      headers: this.headers
    })
  }
  deleteFile(fileInformationId: string) {
    return this.http.delete('http://localhost:8080/uploadFile/' + fileInformationId, {
      headers: this.headers
    })
  }


}
