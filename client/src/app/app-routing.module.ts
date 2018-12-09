import { RegisterComponent } from './auth/register/register.component';
import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { PrivatelessonsComponent } from './services/privatelessons/privatelessons.component';

const routes: Routes = [
  { path: '', component: AppComponent },
  { path: '', component: RegisterComponent },
  { path: '', component: LoginComponent },
  { path: '', component: PrivatelessonsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports:
    [RouterModule]

})
export class AppRoutingModule { }
