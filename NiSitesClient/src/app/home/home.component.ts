import { Component, OnInit } from '@angular/core';
import { User } from '../users/user';
import { Site } from '../sites/site';
import { SiteDataService } from '../sites/site.service';
import { UserDataService } from '../users/user.service';
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
  canBeDeleted = true;
  users: User[] = [];

  constructor(private siteService: SiteDataService, private userService: UserDataService, private router: Router) {
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

  goToSitePages(siteId) {
    debugger;
    this.router.navigate(['/site-pages', siteId]);
  }

  getSites() {
    this.siteService.getAllSites(false)
      .map((data: any) => data.json())
      .subscribe(
      (data: any) => {
        this.sites = data;
      },
      err => console.log(err)
      );
    this.canBeDeleted = false;
  }

  private loadAllSites(deletedSites: boolean) {
    this.siteService.getSites(this.currentUser.id, deletedSites)
      .map((data: any) => data.json())
      .subscribe(
      (data: any) => {
        this.sites = data;
      },
      err => console.log(err)
      );
    this.canBeDeleted = true;
  }

  removeSite(siteId: string) {
    this.siteService.remove(siteId)
      .subscribe(
      () => {
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
      () => {
        this.loadAllSites(this.deletedSites);
        this.userService.getUsers().map((data: any) => data.json())
          .subscribe(
          (data) => {
            this.users = data;
            for (let user of data) {
              debugger;
              this.userService.sendMail(this.currentUser.email,
                this.currentUser.password,
                user.email,
                "Site created",
                "User " + this.currentUser.email + " added a new site")
                .map((data: any) => data.json())
                .subscribe(
                () => {
                },
                err => console.log(err)
                );
            }
          },
          error => {
            console.log(error);
          });

      }, error => {
        console.log(error);
      });
  }
}