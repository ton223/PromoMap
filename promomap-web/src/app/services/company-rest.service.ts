import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

import { Company } from '../models/Company';
import { Utils } from '../utils/utils';

@Injectable()
export class CompanyService {
  private utils = new Utils();
  private path = this.utils.getUrl('company/');

  constructor(private http: Http) { }

  public create(company: Company): Promise<any> {
    return this.http.post(this.path, company, this.utils.getHeaders())
      .toPromise()
      .then(this.utils.extractData)
      .catch(this.utils.handleError);
  }

  public list(): Promise<any> {
    return this.http.get(this.path+'list', this.utils.getHeaders())
      .toPromise()
      .then(this.utils.extractData)
      .catch(this.utils.handleError);
  }

}
