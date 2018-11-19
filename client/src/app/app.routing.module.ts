import { LoginService } from './auth/login/login.service';
import { LoginGuard } from './auth/login/login-guard.service';
import { RegisterComponent } from './auth/register/register.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UserDetailsComponent } from './dashboard/user-details/user-details.component';
const routes: Routes = [
    {path: '', redirectTo: 'login', pathMatch: 'full'},
    {path: 'register', component: RegisterComponent},
    {path: 'login', component: LoginComponent},
    {path: 'dashboard',
    children: [
      {path: '', component: DashboardComponent},
      {path: 'details',component: UserDetailsComponent}], canActivate: [LoginGuard]}
   ]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  providers: [LoginService,LoginGuard],
  exports: [RouterModule]
  
})
export class AppRoutingModule { }