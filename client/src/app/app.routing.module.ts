import { StartComponent } from './auth/start/start.component';
import { PrivatelessonsComponent } from './dashboard/privatelessons/privatelessons.component';
import { EditpostComponent } from './dashboard/posts/editpost/editpost.component';
import { PostdetailComponent } from './dashboard/posts/postdetail/postdetail.component';
import { AddpostComponent } from './dashboard/posts/addpost/addpost.component';
import { PostsComponent } from './dashboard/posts/posts.component';
import { ChangePasswordComponent } from './dashboard/user-details/change-password/change-password.component';
import { LoginService } from './auth/login/login.service';
import { LoginGuard } from './auth/login/login-guard.service';
import { RegisterComponent } from './auth/register/register.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UserDetailsComponent } from './dashboard/user-details/user-details.component';
import { FileUploadComponent } from './dashboard/file-upload/file-upload.component';
const routes: Routes = [
    {path: '', redirectTo: 'start', pathMatch: 'full'},
    {path: 'start', component: StartComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'login', component: LoginComponent},
    {path: 'dashboard',
        children: [
          {path: '', component: DashboardComponent},
          {path: 'resources', component: FileUploadComponent},
          {path: 'details',component: UserDetailsComponent},
          {path: 'privatelessons', component: PrivatelessonsComponent},
          {path: 'changePassword', component: ChangePasswordComponent},
          {path: 'posts', component: PostsComponent, children:[
            {path: ':id', component: PostdetailComponent},
            {path: ':id/editpost', component: EditpostComponent}
          ]},
          {path: 'addpost', component: AddpostComponent}]
    ,canActivate: [LoginGuard]}
   ]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  providers: [LoginService,LoginGuard],
  exports: [RouterModule]
  
})
export class AppRoutingModule { }