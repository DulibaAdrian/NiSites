import { Site } from '../sites/site';

export class User {

  public id: string;
  public email: string;
  public name: string;
  public password: string;
  public site: Site;
}