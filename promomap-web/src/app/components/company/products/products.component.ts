import { Component, OnInit, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  private error: boolean;
  private errorMessage: string;
  private modal: any;
  constructor(@Inject(DOCUMENT) private document: any) { }

  ngOnInit() {
  	this.modal = this.document.getElementsByTagName('app-create-product');
  }

  public openCreateProductModal() {
  	this.modal[0].firstChild.click();
  }

}
