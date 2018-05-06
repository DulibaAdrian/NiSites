import { Component, OnInit } from '@angular/core';
import { User } from '../users/user';
import { UserDataService } from '../users/user.service';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

@Component({
  moduleId: module.id,
  templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit {
  currentUser: User;
  users: User[] = [];

  constructor(private userService: UserDataService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.loadAllUsers();
  }

  private loadAllUsers() {
    this.userService.getUsers()
      .map((data: any) => data.json())
      .subscribe(
        (data: any) => {
          this.users = data;
        },
        err => console.log(err), // error
        () => console.log('getUserStatus Complete') // complete
      );
  }
}