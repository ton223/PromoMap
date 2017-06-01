import { Component, OnInit, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { CompanyService } from '../../../services/rest/company-rest.service';
import { productDAO } from '../../../dao/product-dao';
import { Product }  from '../../../models/Product';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css'],
  providers: [CompanyService]
})
export class ProductsComponent implements OnInit {

  private error: boolean;
  private errorMessage: string;
  private modal: any;
  private companyId: string;

  private products = productDAO.products;
  constructor(private router: Router, @Inject(DOCUMENT) private document: any, private companyService: CompanyService) { }

  ngOnInit() {
  	this.modal = this.document.getElementsByTagName('app-create-product');
    let parts = this.router.url.split('/');
    let last = parts.length-1;
    this.companyId = parts[last];
    this.companyService.listProducts(this.companyId).then(
      response => {
        if (response.status === 200) {
          const task = response.entity;
          if (task.success) {
            productDAO.addProducts(task.dataList);
          } else {
            this.error = true;
            this.errorMessage = task.errorMessage;
          }
        } else {
          this.error = true;
          this.errorMessage = response.statusInfo;
        }
      },
      error => {
        this.errorMessage = <any>error;
        this.error = true;
      }
    );
  }

  public deleteProduct(product: any) {
    this.companyService.deleteProduct(this.companyId, product.superId).then(
      response => {
        if (response.status === 200) {
          const task = response.entity;
          if (task.success) {
            productDAO.remove(product);
          } else {
            this.error = true;
            this.errorMessage = task.errorMessage;
          }
        } else {
          this.error = true;
          this.errorMessage = response.statusInfo;
        }
      },
      error => {
        this.errorMessage = <any>error;
        this.error = true;
      }
    );
   
  }

  public openCreateProductModal() {
  	this.modal[0].firstChild.click();
  }

}
