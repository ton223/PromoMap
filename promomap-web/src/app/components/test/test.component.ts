import { Component, OnInit } from '@angular/core';
import { User } from '../../models/User';
import { UserService } from '../../services/user-rest.service';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css'],
  providers: [UserService]
})
export class TestComponent implements OnInit {

  private users: User[];
  private errorMessage: string;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.list().then(
      users => this.users = users,
      error => this.errorMessage = <any>error);
  }
}
