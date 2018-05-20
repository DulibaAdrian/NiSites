import {AppRoutingModule} from './app-routing.module';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {AppComponent} from './app.component';
import {UsersComponent} from './users/users.component';
import {PagesComponent} from './pages/page.component';
import {UserDataService} from './users/user.service';
import {PageDataService} from './pages/page.service';
import {SiteDataService} from './sites/site.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AlertService } from './alert/alert.service';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AuthenticationService } from './authentication/authentication.service';
import {enableProdMode} from '@angular/core';
import { SafePipe } from './safe.pipe'

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UsersComponent,
    PagesComponent,
    LoginComponent,
    RegisterComponent,
    NavbarComponent,
    SafePipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
   NgbModule.forRoot()
  ],
  providers: [UserDataService, AlertService, AuthenticationService, SiteDataService, PageDataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
