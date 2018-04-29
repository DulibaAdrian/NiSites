import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserDataService } from '../user.service';

@Component({
  selector: 'users-list',
  templateUrl: './users.component.html',
})
export class UsersComponent implements OnInit {
  users: User[];
  selectedUser: User;

  constructor(private userDataService: UserDataService) { }

  getUsers() {
    this.userDataService.getUsers().then(users => this.users = users);
  }

  ngOnInit(): void {
    this.getUsers();
  }
  onSelect(user: User): void {
    this.selectedUser = user;
  }
}