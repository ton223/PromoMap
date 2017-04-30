import { Injectable } from '@angular/core';
import { Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SessionDAO } from '../dao/session-dao';
import { Request } from '../models/Request';
@Injectable()
export class Utils {

  private URL = 'http://127.0.0.1:8080/';

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

  public createRequest(data: any): Request {
    const request = new Request();
    request.setData(data);
    request.setToken(SessionDAO.getToken());
    return request;
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
