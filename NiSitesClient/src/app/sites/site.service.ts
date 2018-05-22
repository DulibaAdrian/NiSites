
import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Site } from './site';

@Injectable()
export class SiteDataService {

  private sitesUrl = 'site/';

  constructor(private http: Http) { }

  getSites(id: string, deletedSites: boolean) {
    return this.http.get(this.sitesUrl + id + "/" + deletedSites);
  }

  getAllSites(deletedSites: boolean) {
    return this.http.get(this.sitesUrl + deletedSites);
  }
  getSiteById(id: string) {
    return this.http.get(this.sitesUrl + "getSiteById/" + id);
  }

  create(site: Site, userId: string) {
    return this.http
      .post(this.sitesUrl + userId, site);
  }

  remove(siteId: string) {
    return this.http.delete(this.sitesUrl + siteId);
  }

}