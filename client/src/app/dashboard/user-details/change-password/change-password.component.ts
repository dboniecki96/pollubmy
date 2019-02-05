import { LoginService } from './../../../auth/login/login.service';
import { DashboardService } from './../../dashboard.service';
import { Router } from '@angular/router';
import { NgForm, FormsModule } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common'
@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  errorStatus: number;
  constructor(private router: Router, private dashboardService: DashboardService, private location: Location, private loginService: LoginService) { }

  ngOnInit() {
  }

  changePassword(f: NgForm) {
    console.log(f);
    const newPassword = f.value
    console.log(newPassword);
    if (confirm('Czy na pewno chcesz zmienić hasło? Zostaniesz przekierowany do strony logowania!')) {
      this.dashboardService.changePassword(newPassword).subscribe(
        res => {
          this.loginService.logoutUser();
        },
        err => {
          this.errorStatus = err.status;
        });
    }
  }
  getErrorStatus() {
    return this.errorStatus;
  }
  backClicked() {
    this.location.back();
  }
}
