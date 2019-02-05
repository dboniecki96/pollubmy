import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { User } from '../../user-models/user-model';
import { DashboardService } from '../../dashboard.service';
@Component({
  selector: 'app-profile-details',
  templateUrl: './profile-details.component.html',
  styleUrls: ['./profile-details.component.css']
})
export class ProfileDetailsComponent implements OnInit {

  disabled = true;
  public user: User[] = [];
  public currentUser: User;
  public editedUser: User;
  isEdited = false;
  constructor(public dashboardService: DashboardService) { }

  ngOnInit() {
    this.dashboardService.getUser()
      .subscribe(res => {
        if (!this.isEdited) {
          this.user.push(res);
        } else {
          this.user.splice(0, 1, res);
        }
        this.currentUser = res;
      }, err => console.log(err));
  }

  filterUserDetails() {
    return this.user.map(x => x.userDetails);
  }
  filterUserAddress() {
    return this.user.map(x => x.userAddress);
  }

  onEditDetails(put: NgForm) {
    const editedUser = put.value;

    if (editedUser.login === this.currentUser.login) {
      editedUser.login = null;
    }
    if (editedUser.emailPollub === this.currentUser.emailPollub) {
      editedUser.emailPollub = null;
    }
    this.dashboardService.editUser(editedUser).subscribe(
      res => {
        this.isEdited = true;
        this.ngOnInit();
      }, err => {
        console.log(err);
      }
    )
  }


}

