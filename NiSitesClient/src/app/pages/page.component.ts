import { Component, OnInit } from '@angular/core';
import { Page } from './page';
import { Site } from '../sites/site';
import { PageDataService } from './page.service';
import { SiteDataService } from '../sites/site.service';

import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'users-list',
  templateUrl: './pages.component.html',
})
export class PagesComponent implements OnInit {
  siteId: string;
  private sub: any;
  site: Site;

  constructor(private pageDataService: PageDataService, private route: ActivatedRoute, private siteDataService: SiteDataService) { }

  getPages() {
    this.pageDataService.getPages(this.siteId);
  }

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      this.siteId = params['id'];
      this.site = this.siteDataService.getSites();
      this.getPages();
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}