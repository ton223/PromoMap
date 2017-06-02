import { Component, OnInit, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';

import { TermsModalComponent } from '../terms-modal/terms-modal.component';

import { User } from '../../models/User';
import { UserService } from '../../services/rest/user-rest.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [UserService]
})
export class RegisterComponent {

  private user = new User();
  private registred: boolean;
  private error: boolean;
  private errorMessage: string;
  private errorCode: number;
  constructor(private userService: UserService, @Inject(DOCUMENT) private document: any) { } // 

  public isValidEmail(): boolean {
    const email: string = this.user.getEmail();
    if (email !== undefined && email != null && email.length > 5 && email.includes('@')) {
      return true;
    } else {
      return false;
    }

  }

  public register(): void {
    this.userService.create(this.user).then(
      response => {
        if (response.status === 200) {
          const task = response.entity;
          if (task.success) {
            this.registred = true;
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

  public openTermsModal() {
  	let termsModal = this.document.getElementsByTagName('app-terms-modal');
    termsModal[0].firstChild.click();
  }
}
