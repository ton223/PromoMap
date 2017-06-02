import { Injectable } from '@angular/core';
import { Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SessionDAO } from '../dao/session-dao';
@Injectable()
export class Utils {

  private URL = 'http://192.168.25.15:8080/';

  public getUrl(path: string) {
    return this.URL + path;
  }

  public getHeaders() {
    const options = new RequestOptions({
      headers: new Headers({
        'Accept': 'application/json',
        'token': SessionDAO.getToken()
      })
    });
    return options;
  }

  public extractData(response: Response) {
    const data = response.json();
    return data || {};
  }

  public handleError(error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}
