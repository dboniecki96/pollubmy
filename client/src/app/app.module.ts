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
import {FormsModule} from '@angular/forms';
import { UserDetailsComponent } from './dashboard/user-details/user-details.component';
import { ProfileDetailsComponent } from './dashboard/user-details/profile-details/profile-details.component';
import { LessonsDetailsComponent } from './dashboard/user-details/lessons-details/lessons-details.component';
import { PostsDetailsComponent } from './dashboard/user-details/posts-details/posts-details.component';
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    RegisterComponent,
    LoginComponent,
    StartComponent,
    DashboardComponent,
    UserDetailsComponent,
    ProfileDetailsComponent,
    LessonsDetailsComponent,
    PostsDetailsComponent,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [LoginService,LoginGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
