import { Injectable } from '@angular/core';
import {Http, URLSearchParams, Headers} from '@angular/http';

import { Config } from '../config';
import { User } from '../models/User';

@Injectable()
export class UserService {

	constructor(private http: Http, private config: Config) {
	}

  	private path : string = this.config.getUrl('user/');

	public register(user: User) {
		let userJson = JSON.stringify({user});

		this.http.post(this.path + 'create', userJson, this.config.getHeaders())
		.subscribe(
		  data => {
		    
		  }
		);
	}
}