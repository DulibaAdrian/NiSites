import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { User } from '../users/user';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html'
})

export class NavbarComponent {
  currentUser: User;

  constructor(private router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }
  logOut() {
    localStorage.removeItem('currentUser');
    this.router.navigate(['/login']);
  }
}