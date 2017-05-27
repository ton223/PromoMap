import { Component, Inject, OnInit } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {

	private terms: boolean;
	private test: string;
  constructor(@Inject(DOCUMENT) private document: any) { }

  ngOnInit() {
  	
  }

  public open() {
  	this.document.getElementById("openModalButton").click();
  }
}
