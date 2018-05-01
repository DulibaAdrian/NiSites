
import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { User } from './user';

@Injectable()
export class UserDataService {

  private usersUrl = 'user/';

  constructor(private http: Http) { }

  getUsers() {
    return this.http.get(this.usersUrl);
  }

  create(user: User) {
    return this.http
      .post(this.usersUrl, user);
  }

}