import { Component } from '@angular/core';
import { User } from '../../models/User';

@Component({
  	selector: 'app-register',
  	templateUrl: './register.component.html',
  	styleUrls: ['./register.component.css']
})
export class Register {
	private user: User;

}