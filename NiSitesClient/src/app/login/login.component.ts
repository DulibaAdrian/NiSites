import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from '../alert/alert.service';
import { AuthenticationService } from '../authentication/authentication.service';

@Component({
  templateUrl: './login.component.html'
})

export class LoginComponent implements OnInit {
  model: any = {};
  loading = false;
  returnUrl: string;

  constructor(
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
        debugger;
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
