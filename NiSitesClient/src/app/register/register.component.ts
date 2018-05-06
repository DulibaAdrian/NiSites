import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserDataService } from '../users/user.service';
import { AlertService } from '../alert/alert.service';

@Component({
  templateUrl: './register.component.html'
})

export class RegisterComponent {
  model: any = {};
  loading = false;

  constructor(
    private router: Router,
    private userService: UserDataService,
    private alertService: AlertService) { }

  register() {
    this.loading = true;
    this.userService.create(this.model)
      .subscribe(
      data => {
          this.alertService.success('Registration successful', true);
          this.router.navigate(['/login']);
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }
}
