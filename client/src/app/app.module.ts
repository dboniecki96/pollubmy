import { PrivatelessonsService } from './dashboard/privatelessons/privatelessons.service';
import { CompareValidatorDirective } from './shared/compare-validator.directive';
import { NgbdModaladdAsTeacher } from './dashboard/privatelessons/modal-addAsTeacher';
import { NgbdModaladdAsStudent } from './dashboard/privatelessons/modal-addAsStudent';
import { AddprivatelessonComponent } from './dashboard/privatelessons/addprivatelesson/addprivatelesson.component';
import { RegisterService } from './auth/register/register.service';
import { PostdetailComponent } from './dashboard/posts/postdetail/postdetail.component';
import { AddpostComponent } from './dashboard/posts/addpost/addpost.component';
import { AppRoutingModule } from './app.routing.module';
import { LoginService } from './auth/login/login.service';
import { LoginGuard } from './auth/login/login-guard.service';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { StartComponent } from './auth/start/start.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header/header.component';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from './auth/login/login.component';
import { Router, RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RegisterService } from './auth/register/register.service';
import { CompareValidatorDirective } from './shared/compare-validator.directive';
import {FormsModule} from '@angular/forms';
import { UserDetailsComponent } from './dashboard/user-details/user-details.component';
import { ProfileDetailsComponent } from './dashboard/user-details/profile-details/profile-details.component';
import { LessonsDetailsComponent } from './dashboard/user-details/lessons-details/lessons-details.component';
import { PostsDetailsComponent } from './dashboard/user-details/posts-details/posts-details.component';
<<<<<<< HEAD

const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
]

=======
import { FileUploadComponent } from './dashboard/file-upload/file-upload.component';
import { ChangePasswordComponent } from './dashboard/user-details/change-password/change-password.component';
import { PostsComponent } from './dashboard/posts/posts.component';
import { EditpostComponent } from './dashboard/posts/editpost/editpost.component';
import { FileDetailsComponent } from './dashboard/user-details/file-details/file-details.component';
import { PrivatelessonsComponent } from './dashboard/privatelessons/privatelessons.component';
import { HttpModule } from '@angular/http';
import { ModalModule } from 'ngx-bootstrap/modal';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
>>>>>>> origin/Upload_2F
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    RegisterComponent,
    LoginComponent,
    StartComponent,
    CompareValidatorDirective,
    DashboardComponent,
    UserDetailsComponent,
    ProfileDetailsComponent,
    LessonsDetailsComponent,
    PostsDetailsComponent,
<<<<<<< HEAD
=======
    FileUploadComponent,
    ChangePasswordComponent,
    PostsComponent,
    AddpostComponent,
    PostdetailComponent,
    EditpostComponent,
    FileDetailsComponent,
    PrivatelessonsComponent,
    AddprivatelessonComponent,
    NgbdModaladdAsStudent,
    NgbdModaladdAsTeacher,
    CompareValidatorDirective,
>>>>>>> origin/Upload_2F
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule,
    ModalModule.forRoot(),
    TabsModule.forRoot()
  ],
  providers: [LoginService, RegisterService,HeaderComponent,LoginGuard, PrivatelessonsService, NgbCarouselConfig],
  bootstrap: [AppComponent]
})
export class AppModule { }
