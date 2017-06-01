import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

import { Utils } from '../../utils/utils';

@Injectable()
export class ImageService {
  private utils = new Utils();
  private apiLink = "https://api.cloudinary.com/v1_1/luk40cloud";
  constructor(private http: Http) { }

  public uploadImages(uploader) {
    const params = this.createUploadParams(uploader);
    return this.upload(params).toPromise()
      .then(this.utils.extractData)
      .catch(this.utils.handleError);
  }

  public getDefaultImageURL(): string {
    return "http://res.cloudinary.com/luk40cloud/image/upload/v1495925270/tnqrpzoyessdzwokijak.jpg";
  }

  private createUploadParams(uploader) {
    let formData = new FormData();
    formData.append('upload_preset', "promomap-products");
    formData.append('file', uploader.queue[0]._file);
    return formData;
  }

  private upload(params) {
    let headers = new Headers();
    headers.append('X-Requested-With', 'XMLHttpRequest');
    return this.http.post(this.apiLink+'/upload', params, {headers});
  }

}
