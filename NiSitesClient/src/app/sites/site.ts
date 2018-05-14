import { Page } from '../pages/page';

export class Site {

  public id: string;
  public creationDate: Date;
  public url: string;
  public siteName: string;
  public pages: Page[];
  public isDeleted: boolean;
}