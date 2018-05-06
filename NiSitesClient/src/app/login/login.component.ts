﻿import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from '../alert/alert.service';
import { AuthenticationService } from '../authentication/authentication.service';
import { User } from '../users/user';


@Component({
  templateUrl: './login.component.html'
})

export class LoginComponent implements OnInit {
  model: any = {};
  loading = false;
  returnUrl: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private alertService: AlertService,
    private authenticationService: AuthenticationService
    ) { }

  ngOnInit() {
    this.authenticationService.logout();
  }

  login() {
    this.loading = true;
    this.authenticationService.login(this.model.email, this.model.password)
      .map((data: any) => data.json())
      .subscribe(
      (data: any) => {
        //login succeed
          localStorage.setItem('currentUser', JSON.stringify(data));
          this.router.navigate(['/home']);
        },
        err => {
          //login failed
          this.loading = false;
          this.router.navigate(['/login'])
        } ,
        () => console.log('getUserStatus Complete') // complete
      );
  }
}
