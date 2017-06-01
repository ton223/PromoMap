import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

import { User } from '../../models/User';
import { Marker } from '../../models/Marker';
import { Utils } from '../../utils/utils';

@Injectable()
    export class ProductService {
    private utils = new Utils();
    private path = this.utils.getUrl('product/');

    constructor(private http: Http) { }

    public listProductsInRadius(userLocation: Marker): Promise<any> {
    return this.http.post(this.path+'list-radius', userLocation, this.utils.getHeaders())
        .toPromise()
        .then(this.utils.extractData)
        .catch(this.utils.handleError);
    }

}
