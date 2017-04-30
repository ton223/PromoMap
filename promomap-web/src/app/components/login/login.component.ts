import { Component, OnInit } from '@angular/core';
import { User } from '../../models/User';
import { UserService } from '../../services/user-rest.service';
import { AppComponent } from '../../app.component';
import { SessionDAO } from '../../dao/session-dao';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [UserService]
})
export class LoginComponent implements OnInit {

  private user = new User();
  private logged: boolean;

  constructor(private userService: UserService) { }

  ngOnInit() {
    if (SessionDAO.hasToken()) {
      this.logged = true;
      this.user = SessionDAO.getUser();
    } else {
      this.logged = false;
    }
  }

  private login(): void {
    this.userService.login(this.user).then(
      response => {
        const task = response.entity;
        if (task.success) {
          this.logged = true;
          SessionDAO.setToken(task.info);
          SessionDAO.setUser(task.data);
          this.user = task.data;
        } else {

        }
      }
    );
  }

  private logout() {
    this.userService.logout().then(
      response => {
        const task = response.entity;
        if (task.success) {
          SessionDAO.clearSession();
          this.logged = false;
        }
      }
    );
  }
}
