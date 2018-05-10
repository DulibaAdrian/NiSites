import { Component, OnInit } from '@angular/core';
import { User } from '../users/user';
import { Site } from '../sites/site';
import { SiteDataService } from '../sites/site.service';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import { Router} from '@angular/router';


@Component({
  moduleId: module.id,
  templateUrl: 'home.component.html',
  styleUrls: ['home.component.css']
})

export class HomeComponent implements OnInit {
  currentUser: User;
  sites: Site[] = [];

  constructor(private siteService: SiteDataService, private router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.loadAllSites();
  }

  private loadAllSites() {
    this.siteService.getSites(this.currentUser.id)
      .map((data: any) => data.json())
      .subscribe(
      (data: any) => {
        this.sites = data;
      },
      err => console.log(err), // error
      () => console.log('getUserStatus Complete') // complete
      );
  }

  saveSite(siteName: string) {

    var site = new Site();
    site.url = siteName;
    this.siteService.create(site, this.currentUser.id)
      .subscribe(
      data => {
        this.onRefresh();
      },
      error => {
        console.log(error);
      });
  }

  onRefresh() {
    this.router.routeReuseStrategy.shouldReuseRoute = function () { return false; };

    let currentUrl = this.router.url + '?';

    this.router.navigateByUrl(currentUrl)
      .then(() => {
        this.router.navigated = false;
        this.router.navigate([this.router.url]);
      });
  }
}