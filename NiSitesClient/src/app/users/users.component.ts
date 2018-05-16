import { Component, OnInit } from '@angular/core';
import { User } from './user';
import { UserDataService } from './user.service';

@Component({
  selector: 'users-list',
  templateUrl: './users.component.html',
})
export class UsersComponent implements OnInit {
  selectedUser: User;

  constructor(private userDataService: UserDataService) { }

  getUsers() {
    this.userDataService.getUsers();
  }

  ngOnInit(): void {
    this.getUsers();
  }

}