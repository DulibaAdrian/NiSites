import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Page } from './page';

@Injectable()
export class PageDataService {

  private pageUrl = 'page/';

  constructor(private http: Http) { }

  getPages(siteId: string) {
    return this.http.get(this.pageUrl + siteId);
  }

  getPageById(pageId: string) {
    return this.http.get(this.pageUrl + "getPageById/" + pageId);
  }

  create(page: Page, siteId: string) {
    return this.http
      .post(this.pageUrl + siteId, page);
  }

  remove(pageId: string) {
    return this.http.delete(this.pageUrl + pageId);
  }

  editPageContent(pageId: string, pageContent: string) {
    return this.http.put(this.pageUrl + "editPageContent/" + pageId, pageContent);
  }

  editPageHeader(pageId: string, pageHeader: string) {
    return this.http.put(this.pageUrl + "editPageHeader/" + pageId, pageHeader);
  }
}