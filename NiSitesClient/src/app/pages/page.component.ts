import { Component, OnInit } from '@angular/core';
import { Page } from './page';
import { Site } from '../sites/site';
import { PageDataService } from './page.service';
import { SiteDataService } from '../sites/site.service';

import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'users-list',
  templateUrl: './pages.component.html',
  styleUrls: ['page.component.css']
})

export class PagesComponent implements OnInit {
  siteId: string;
  private sub: any;
  site: Site;
  pages: Page[];
  currentPage: Page;

  constructor(private pageDataService: PageDataService, private route: ActivatedRoute, private siteDataService: SiteDataService) { }

  getPages() {
    this.pageDataService.getPages(this.siteId)
      .map((data: any) => data.json())
      .subscribe(
      (data: any) => {
        debugger;
        this.pages = data;
      },
      err => console.log(err)
      );
  }

  getSite() {
    this.siteDataService.getSiteById(this.siteId)
      .map((data: any) => data.json())
      .subscribe(
      (data: any) => {
        debugger;
        this.site = data;
      },
      err => console.log(err)
      );
  }

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      this.siteId = params['siteId'];
      this.getSite();
      this.getPages();
    });
  }

  removePage(pageId: string) {
    this.pageDataService.remove(pageId)
      .subscribe(
        () => {
          this.getPages();
        },
        error => {
          console.log(error);
        });
  }

  savePage(pageName: string) {
    var page = new Page();
    page.pageName = pageName;
    this.pageDataService.create(page, this.siteId)
      .subscribe(
      () => {
        this.getPages();
      },
      error => {
        console.log(error);
      });
  }

  showPageContent(pageId: string) {
    this.pageDataService.getPageById(pageId)
      .map((data: any) => data.json())
      .subscribe(
      (data) => {
        debugger;
          this.currentPage = data;
        },
        error => {
          console.log(error);
        });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}