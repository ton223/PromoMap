import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionDAO } from '../../../dao/session-dao';
import { User } from '../../../models/User';
import { UserService } from '../../../services/rest/user-rest.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  providers: [UserService]
})
export class ProfileComponent implements OnInit {

  private user: User;
  private userEdited: User;
  private isEditing: boolean;
  private error: boolean;
  private success: boolean;
  private errorMessage: string;
  private genders = [
    {name: 'MALE', description: "Masculino"},
    {name: 'FEMALE', description: "Feminino"},
    {name: 'OTHER', description: "Outro"}
  ];
  
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.user = SessionDAO.getUser();
  }
  
  public setEditing() {
    this.userEdited = Object.assign(new User(), this.user);
    this.isEditing = true;
  }
  
  public cancelEdit() {
    this.isEditing = false;
    this.error = false;
    this.errorMessage = null;
  }
  
  public editUser() {
    if(this.userEdited.equals(this.user)) {
      this.isEditing = false;
      return;
    }
    this.userService.edit(this.userEdited).then(
      response => {
        if (response.status === 200) {
          const task = response.entity;
          if (task.success) {
            this.success = true;
            this.isEditing = false;
            this.user = Object.assign(new User(), this.userEdited);
            SessionDAO.setUser(this.user);
          } else {
            this.error = true;
            this.errorMessage = task.errorMessage;
          }
        } else {
          this.error = true;
          this.errorMessage = response.statusInfo;
        }
      },
      error => {
        this.errorMessage = <any>error;
        this.error = true;
      } 
    );
  }

}
