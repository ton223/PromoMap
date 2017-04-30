import { Component } from '@angular/core';
import { User } from '../../models/User';
import { UserService } from '../../services/user-rest.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
  providers: [UserService]
})
export class SidebarComponent {

}
