import { Component } from '@angular/core';
import { User } from '../../models/User';
import { UserService } from '../../services/user-rest.service';

@Component({
  	selector: 'app-register',
  	templateUrl: './register.component.html',
  	styleUrls: ['./register.component.css']
})
export class Register {

//	private userService = new UserService();

	private user = new User();

	public register():void {
//		this.userService.register(this.user);
	}
}