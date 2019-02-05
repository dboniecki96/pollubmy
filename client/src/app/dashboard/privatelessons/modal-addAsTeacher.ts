import { Component, OnInit } from '@angular/core';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Faculty } from '.././faculty-models/faculty.model';
import { FieldOfStudy } from '.././faculty-models/fieldOfStudy.model';
import { PrivatelessonsService } from './privatelessons.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'ngbd-modal-addAsTeacher',
  templateUrl: './modal-addAsTeacher.html',
  styleUrls: ['./modal-addAsTeacher.css']
})
export class NgbdModaladdAsTeacher implements OnInit {
  faculties: Faculty[];
  facultySelected: boolean;
  selectedFaculty: string;
  fieldSelected: boolean;
  selectedField: string;
  fields: FieldOfStudy[];
  courses: string[];
  selectedF: Faculty;
  selectedC: string[];
  courseSelected: boolean;
  selectedCourse: string;

  constructor(private modalService: NgbModal, private lessonService: PrivatelessonsService, private router: Router) { }
  ngOnInit() {
    this.lessonService.getFaculties().subscribe(
      res => {
        this.faculties = res;
        console.log(res);
      },
      err => {
        console.log(err);
      }
    );
  }
  open(content) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-addAsTeacher-title' }).result.then((result) => {
    });
  }
  onSelectFaculty(event) {
    let value = event.target.options;
    if (event) {
      this.facultySelected = false;
      this.selectedFaculty = value[value.selectedIndex].innerHTML;
      this.fields = this.faculties.find(faculty => faculty.faculty === this.selectedFaculty).fieldOfStudies;
      console.log(this.selectedFaculty);
    }
  }
  onSelectField(event) {
    let value = event.target.options;
    if (event) {
      this.fieldSelected = false;
      this.selectedField = value[value.selectedIndex].innerHTML;
      this.courses = this.faculties.find(faculty => faculty.faculty === this.selectedFaculty)
        .fieldOfStudies.find(field => field.fieldOfStudy === this.selectedField).courses;
      console.log(this.courses);
      console.log(this.selectedField);
    }
  }
  onSelectCourse(event) {
    let value = event.target.options;
    if (event) {
      this.courseSelected = false;
      this.selectedCourse = value[value.selectedIndex].innerHTML;
      console.log(this.selectedCourse);
    }
  }
  onSubmit(privatelessonsAdd: NgForm) {
    const value = privatelessonsAdd.value;
    console.log(privatelessonsAdd.value)
    this.lessonService.addPrivatelesson(value.time, value.price, value.description, value.faculty, value.fieldOfStudy, value.course)
      .subscribe(
        (response) => {
          console.log(response)
          this.router.navigate(['./privatelessons']);
        },
        (error) => {
          console.log(error);
        }
      )
  }
}

