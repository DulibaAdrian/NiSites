import {AppRoutingModule} from './app-routing.module';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {UserDetailsComponent} from './user-details/user-details.component';
import {UsersComponent} from './users/users.component';
import {UserDataService} from './user.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import {enableProdMode} from '@angular/core';

@NgModule({
  declarations: [
    AppComponent,
    UserDetailsComponent,
    UsersComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
   NgbModule.forRoot()
  ],
  providers: [UserDataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
