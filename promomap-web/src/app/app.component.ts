import { Component } from '@angular/core';

@Component({
  	selector: 'app-root',
  	templateUrl: './app.component.html',
  	styleUrls: ['./app.component.css']
})
export class AppComponent {
	private login: string='';
	private password: string='';
	private logged = false;
  	private title = 'PromoMap';

  	private loginClick():void {
  		this.login="sim"
  	}
}