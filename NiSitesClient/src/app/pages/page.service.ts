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
    return this.http
      .post(this.pageUrl + siteId, page);
  }

}