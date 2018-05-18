import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { User } from '../users/user';
import {Location} from '@angular/common';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html'
})

export class NavbarComponent {
  currentUser: User;

  constructor(private router: Router, private location: Location) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  backClicked() {
    this.location.back();
  }

  logOut() {
    localStorage.removeItem('currentUser');
    this.router.navigate(['/login']);
  }
}