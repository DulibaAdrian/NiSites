import { Page } from '../pages/page';
import { User } from '../users/user';

export class Site {

  public id: string;
  public creationDate: Date;
  public url: string;
  public siteName: string;
  public pages: Page[];
  public users: User[];
  public isDeleted: boolean;
}