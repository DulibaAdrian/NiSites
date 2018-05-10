﻿
import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Site } from './site';

@Injectable()
export class SiteDataService {

  private sitesUrl = 'site/';

  constructor(private http: Http) { }

  getSites(id: string) {
    return this.http.get(this.sitesUrl + id);
  }

  create(site: Site, userId: string) {
    debugger;
    return this.http
      .post(this.sitesUrl + userId, site);
  }

}