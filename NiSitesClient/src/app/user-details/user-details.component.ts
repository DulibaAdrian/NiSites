import { Component, Input } from '@angular/core';

import { User } from '../user';
import { UserDataService } from '../user.service';

@Component({
  selector: 'user-detail',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css'],
  providers: [UserDataService]
})

export class UserDetailsComponent {

  @Input() user: User;

  constructor(private userDataService: UserDataService) { }

  delete(): void {
    this.userDataService.delete(this.user.id).then(() => this.goBack());
  }

  goBack(): void {
    window.location.replace('');
  }
}