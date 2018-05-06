import { Component, Input } from '@angular/core';

import { User } from '../users/user';
import { UserDataService } from '../users/user.service';

@Component({
  selector: 'user-detail',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css'],
  providers: [UserDataService]
})

export class UserDetailsComponent {

  @Input() user: User;

  constructor(private userDataService: UserDataService) { }

  goBack(): void {
    window.location.replace('');
  }
}