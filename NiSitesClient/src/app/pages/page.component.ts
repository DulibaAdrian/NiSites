import { Component, OnInit } from '@angular/core';
import { Page } from './page';
import { Site } from '../sites/site';
import { User } from '../users/user';
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
  editorEnabled: boolean;
  headerEditorEnabled: boolean;
  currentUser: User;
  canEditOrDelete = false;

  constructor(private pageDataService: PageDataService, private route: ActivatedRoute, private siteDataService: SiteDataService) { }

  getPages() {
    this.pageDataService.getPages(this.siteId)
      .map((data: any) => data.json())
      .subscribe(
      (data: any) => {
        this.pages = data;
        this.showPageContent(this.pages[0].id);
      },
      err => console.log(err)
      );
  }

  getSite() {
    this.siteDataService.getSiteById(this.siteId)
      .map((data: any) => data.json())
      .subscribe(
      (data: any) => {
        this.site = data;
        debugger;
        for (var user of data.userList) {
          if (user.id === this.currentUser.id) {
            this.canEditOrDelete = true;
          }
        }
      },
      err => console.log(err)
      );
  }

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      this.siteId = params['siteId'];
      this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
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

  enableHeaderEditor(content) {
    this.headerEditorEnabled = true;
  }
  disableHeaderEditor() {
    this.headerEditorEnabled = false;
  }

  enableEditor(pageName) {
    this.editorEnabled = true;
  }
  disableEditor() {
    this.editorEnabled = false;
  }

  editContent(content) {
    this.currentPage.content = content;
    this.disableEditor();
    this.pageDataService.editPageContent(this.currentPage.id, content)
      .map((data: any) => data.json())
      .subscribe(
      (data) => {
        this.currentPage = data;
      },
      error => {
        console.log(error);
      });
  };

  editHeader(header) {
    this.currentPage.pageName = header;
    this.disableHeaderEditor();
    this.pageDataService.editPageHeader(this.currentPage.id, header)
      .map((data: any) => data.json())
      .subscribe(
      (data) => {
        this.currentPage = data;
      },
      error => {
        console.log(error);
      });
  };

  showPageContent(pageId: string) {
    this.pageDataService.getPageById(pageId)
      .map((data: any) => data.json())
      .subscribe(
      (data) => {
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