import { DashboardService } from './../dashboard.service';
import { Faculty } from './../faculty-models/faculty.model';
import { FileUploadService } from './file-upload.service';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { NgForm } from '@angular/forms';
import { FileModel } from './file.model';
import { FieldOfStudy } from '../faculty-models/fieldOfStudy.model';
import { BsModalRef, BsModalService } from 'ngx-bootstrap';
@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {

  modalRef: BsModalRef;

  noFilesErrorStatus: number;
  displayBackdrop = 'none';
  openedModal: boolean = false;
  facultySelected: boolean = true;

  selectedFaculty: string;
  selectedField: string;
  selectedCourse: string;

  fieldSelected: boolean = true;
  courseSelected: boolean = true;
  progress: number = 0;

  file: File = null;
  fileName: string;
  fileSize: number;
  fileUploaded: boolean = false;
  files: FileModel[];
  fileExists: boolean;

  facultyExistingFiles: Faculty;
  selectedF: Faculty;
  fields: FieldOfStudy[];
  selectedC: string[];
  courses: string[];
  fieldsWithFiles: FieldOfStudy[];

  faculties: Faculty[];

  constructor(private http: HttpClient, private uploadService: FileUploadService, private dashboardService: DashboardService, private modalSerivce: BsModalService) { }

  ngOnInit() {
    console.log('Initialized');

    this.uploadService.getFaculties().subscribe(
      res => {
        this.faculties = res;
        this.uploadService.getAllFiles().subscribe(
          res => {
            this.files = res;
          }, err => {
            this.noFilesErrorStatus = err.status;
          }
        );
      }, err => {
        console.log(err);
      }
    );
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalSerivce.show(template);
  }
  isThereFile() {
    return this.files.filter(x => x.course === this.selectedCourse && x.fieldOfStudy === this.selectedField).length > 0;
  }

  onSelectFaculty() {
    this.fields = this.faculties.find(x => x.faculty === this.selectedFaculty).fieldOfStudies;
  }
  onSelectField() {
    this.courses = this.fields.find(f => f.fieldOfStudy === this.selectedField).courses;
  }
  onFileSelected(event: any) {
    this.file = <File>event.target.files[0];
    this.fileName = this.file.name;
    this.fileSize = this.file.size;
  }
  onUpload(upload: NgForm) {
    let data: FormData = new FormData();
    const body = upload.value.storedFileBody;
    data.append('file', this.file);
    data.append('storedFileBody', JSON.stringify(body));
    this.uploadService.uploadFile(data).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(event.loaded / event.total * 100);
        } else if (event.type === HttpEventType.Response) {
          upload.reset();
          this.ngOnInit();
          this.fileExists = true;
          this.noFilesErrorStatus = event.status;
          this.fileUploaded = true;
        }
      }, err => {
        console.log(err);
        this.fileUploaded = false;
      }
    )
  }
  downloadFile(fileInformationId: string, fileName: string) {
    this.uploadService.downloadFile(fileInformationId).subscribe(
      res => {
        let url = window.URL.createObjectURL(res);
        let a = document.createElement("a");
        a.download = fileName;
        a.href = url;
        a.click();
      },
      err => console.log(err)
    )
  }
  deleteFile(fileInformationId: string) {
    if (confirm('Czy na pewno chcesz usunąć plik? Zmiany zostaną utracone!')) {
      if (this.files.length === 1) {
        this.files = [];
      }
      this.uploadService.deleteFile(fileInformationId).subscribe(
        res => {
          this.ngOnInit();
        },
        err => console.log(err)
      );
    }
  }


}

