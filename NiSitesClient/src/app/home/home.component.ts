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
  deletedSites: boolean = false;
  constructor(private siteService: SiteDataService, private router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.deletedSites = false;
    this.loadAllSites(this.deletedSites);
  }


  getRemovedSites() {
    this.deletedSites = true;
    this.loadAllSites(this.deletedSites);
  }

  private loadAllSites(deletedSites: boolean) {
    this.siteService.getSites(this.currentUser.id, deletedSites)
      .map((data: any) => data.json())
      .subscribe(
      (data: any) => {
        this.sites = data;
      },
      err => console.log(err), // error
      () => console.log('getUserStatus Complete') // complete
      );
  }

  removeSite(siteId: string) {
    debugger;
    console.log(siteId);
    this.siteService.remove(siteId)
      .subscribe(
      data => {
        this.loadAllSites(this.deletedSites);
      },
      error => {
        console.log(error);
      });
  }

  saveSite(siteName: string) {

    var site = new Site();
    site.url = siteName;
    this.siteService.create(site, this.currentUser.id)
      .subscribe(
      data => {
        this.loadAllSites(this.deletedSites);
      },
      error => {
        console.log(error);
      });
  }
}