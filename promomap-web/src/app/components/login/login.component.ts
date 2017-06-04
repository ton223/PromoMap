import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../models/User';
import { UserService } from '../../services/rest/user-rest.service';
import { AppComponent } from '../../app.component';
import { SessionDAO } from '../../dao/session-dao';

import {FacebookService, LoginResponse, LoginOptions} from 'ng2-facebook-sdk';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [UserService]
})
export class LoginComponent implements OnInit {

  private user = new User();
  private logged: boolean;

  constructor(private userService: UserService, private router: Router, private fb: FacebookService) {
  console.log('Initializing Facebook');

   fb.init({
     appId: '1047636085368634',
     cookie: true,
     status: true,
     xfbml: true,
     version: 'v2.9'
   }); }

loginWithOptions() {

    const loginOptions: LoginOptions = {
      enable_profile_selector: true,
      return_scopes: true,
      scope: 'public_profile,email'
    };

    this.fb.login(loginOptions)
      .then((res: LoginResponse) => {
        console.log('Logged in', res);
      })
  }

  getLoginStatus() {
   this.fb.getLoginStatus()
     .then(console.log.bind(console))
     .catch(console.error.bind(console));
 }

  getProfile() {
   this.fb.api('/me?fields=id,first_name,last_name,email')
     .then((res: any) => {
       console.log('Got the users profile', res);
     })
 }

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
          this.router.navigate(['home']);
        } else {

        }
      }
    );
  }

  private logout() {
    this.userService.logout().then(
      response => {
        if (response.status === 200) {
          this.logged = false;
          SessionDAO.clearSession();
        } else {
          this.logged = false;
          SessionDAO.clearSession();
        }
      },
      error => {
        this.logged = false;
        SessionDAO.clearSession();
      }
    );
  }


  onSignIn(googleUser) {
  var profile = googleUser.getBasicProfile();
  var id_token = googleUser.getAuthResponse().id_token;
  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
  console.log('Name: ' + profile.getName());
  console.log('Image URL: ' + profile.getImageUrl());
  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
}
}
