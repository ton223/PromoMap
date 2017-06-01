import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

import { Company } from '../../models/Company';
import { Product } from '../../models/Product';
import { Utils } from '../../utils/utils';

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
    return this.http.get(this.path, this.utils.getHeaders())
      .toPromise()
      .then(this.utils.extractData)
      .catch(this.utils.handleError);
  }

  public delete(companyId: string): Promise<any> {
    return this.http.delete(this.path + companyId, this.utils.getHeaders())
      .toPromise()
      .then(this.utils.extractData)
      .catch(this.utils.handleError);
  }

  public edit(companyId: string, companyEdited: Company): Promise<any> {
    return this.http.put(this.path + companyId, companyEdited, this.utils.getHeaders())
      .toPromise()
      .then(this.utils.extractData)
      .catch(this.utils.handleError);
  }

  public findBySuperId(companyId: string): Promise<any> {
    return this.http.get(this.path + companyId, this.utils.getHeaders())
      .toPromise()
      .then(this.utils.extractData)
      .catch(this.utils.handleError);
  }

  public registerProduct(companyId: string, product: Product): Promise<any> {
    return this.http.post(this.path + companyId + '/product', product, this.utils.getHeaders())
      .toPromise()
      .then(this.utils.extractData)
      .catch(this.utils.handleError);
  }

  public listProducts(companyId: string): Promise<any> {
    return this.http.get(this.path + companyId +'/product', this.utils.getHeaders())
      .toPromise()
      .then(this.utils.extractData)
      .catch(this.utils.handleError);
  }

  public deleteProduct(companyId: string, productId: string) {
    return this.http.delete(this.path + companyId +'/product/'+productId, this.utils.getHeaders())
      .toPromise()
      .then(this.utils.extractData)
      .catch(this.utils.handleError);
  }
}
