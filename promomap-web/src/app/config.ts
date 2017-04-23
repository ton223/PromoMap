import { Injectable } from '@angular/core';
import { Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SessionDAO } from './dao/Session.dao';
 
@Injectable()
export class Config {
 
	private URL: string = 'http://192.168.25.15:3000/';
 
	public getUrl(path: string) {
		return this.URL + path;
	}
 
	public getHeaders() {
		let headersParams = { 'Content-Type': 'application/json' };
		if (SessionDAO.hasToken()) {
			headersParams['Token'] = SessionDAO.getToken();
		}
		let headers = new Headers(headersParams);
    	let options = new RequestOptions({ headers: headers });
    	return options;
	}
 
	public extrairDados(response: Response) {
    	let data = response.json();
    	return data || {};
  	}
 
  	public processarErros(erro: any) {
	    return Observable.throw('Erro acessando servidor remoto.');
	}
}