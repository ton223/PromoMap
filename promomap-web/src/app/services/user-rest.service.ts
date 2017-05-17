import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

import { User } from '../models/User';
import { Utils } from '../utils/utils';

@Injectable()
export class UserService {
  private utils = new Utils();
  private path = this.utils.getUrl('user/');

  constructor(private http: Http) { }

  public create(user: User): Promise<any> {
    return this.http.post(this.path, user, this.utils.getHeaders())
      .toPromise()
      .then(this.utils.extractData)
      .catch(this.utils.handleError);
  }

  public login(user: User): Promise<any> {
    return this.http.post(this.path + 'login', user, this.utils.getHeaders())
      .toPromise()
      .then(this.utils.extractData)
      .catch(this.utils.handleError);
  }

  public logout(): Promise<any> {
    return this.http.post(this.path + 'logout', null,  this.utils.getHeaders())
      .toPromise()
      .then(this.utils.extractData)
      .catch(this.utils.handleError);
  }
  
    public edit(user: User): Promise<any> {
    return this.http.put(this.path, user,  this.utils.getHeaders())
      .toPromise()
      .then(this.utils.extractData)
      .catch(this.utils.handleError);
  }

}
