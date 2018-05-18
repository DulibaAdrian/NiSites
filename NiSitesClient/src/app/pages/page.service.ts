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

  create(page: Page, siteId: string) {
    debugger;
    return this.http
      .post(this.pageUrl + siteId, page);
  }

  remove(pageId: string) {
    return this.http.delete(this.pageUrl + pageId);
  }

}