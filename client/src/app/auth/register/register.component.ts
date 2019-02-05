import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RegisterService } from './register.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  errorStatus: number;

  @ViewChild('f') signupForm: NgForm;

  constructor(private registerService: RegisterService, private router: Router) { }
  ngOnInit() {
  }
  onSubmit(f: NgForm) {

    console.log(f.value);
    this.registerService.storeUsers(f.value)
      .subscribe(
        (response) => {
          console.log(response)
          this.router.navigate(['/login']);
        },
        (error) => {
          this.errorStatus = error.status;
          console.log(error);
          this.signupForm.reset();
        });
  }
  getErrorStatus() {
    return this.errorStatus;
  }
}

