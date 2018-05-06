import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'
import { UserDataService } from '../users/user.service';
import { User } from '../users/user';

@Injectable()
export class AuthenticationService {

  constructor(private userService: UserDataService) { }

  login(email: string, password: string) {
    var user = new User();
    user.email = email;
    user.password = password;
    var userModel = this.userService.authenticate(user);
    return userModel;
  }

  logout() {
    localStorage.removeItem('currentUser');
  }
}