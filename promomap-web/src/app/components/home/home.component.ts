import { Component, OnInit, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';

import { LocationService } from '../../services/location.service';
import { ProductService } from '../../services/rest/product-rest.service';
import { SessionDAO } from '../../dao/session-dao';
import { Graph } from '../../models/Graph';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [LocationService, ProductService]
})
export class HomeComponent implements OnInit {

  private error: boolean;
  private errorMessage: string;
  private showInfo: boolean;
  private infoMessage: string;
  private loading: boolean;

  private productModal: any;
  private clickedProduct: any;

  private graph: Graph;

  private products = [];
  private pages = [{index: 0, products: []}];
  private currentPage = 0;

  private categoryFilter = [
    {name: 'Eletrônicos e celulares', category: 'ELETRONIC', show: true},
    {name: 'Eletrodomésticos', category: 'HOME_APPLIANCES', show: true},
    {name: 'Alimentação', category: 'FOOD', show: true},
    {name: 'Brinquedos', category: 'TOYS', show: true},
    {name: 'Jogos', category: 'GAMES', show: true},
    {name: 'Veículos e barcos', category: 'AUTO', show: true},
    {name: 'Esporte', category: 'SPORT', show: true},
    {name: 'Moda e beleza', category: 'FASHION', show: true},
    {name: 'Bebês e crianças', category: 'KIDS', show: true},
    {name: 'Para a sua casa', category: 'HOME', show: true},
    {name: 'Músicas e hobbies', category: 'MUSIC', show: true},
  ];
  private distanceFilter = 60000;
  private nameFilter: string;

  constructor(private locationService: LocationService, private productService: ProductService, @Inject(DOCUMENT) private document: any) {}

  ngOnInit() {
    this.productModal = this.document.getElementsByTagName('app-product-modal');
    this.loading = true;
    this.errorMessage = this.locationService.updateCurrentUserPosition();
    if(this.errorMessage !== undefined) {
      this.error = true;
    }
    this.listProductsInRadius();
  }

  public populatePages(products) {
    this.pages = [];
    let length = products.length;
    if(length == 0) {
      this.pages = [{index: 0, products: []}];
    }
    let productIndex = 0;
    let pageIndex = 0;
    while(length > productIndex) {
      let pageProducts = [];
      for(let i = productIndex; i < length; i++) {
        pageProducts.push(products[i]);
        productIndex++;
        if(productIndex % 12 == 0) {
          break;
        }
      }
      this.pages.push({index: pageIndex, products: pageProducts});
      pageIndex++;
    }
  }

  public getDiscountedPrice(product: any): number {
    let discountPercent = product.discount;
    let price = product.price;
    let discount = (discountPercent * price) / 100;
    return (price - discount);
  }

  public openProductInfo(product: any) {
    this.productModal[0].firstChild.click();
    console.log(product.name);
  }

  public listProductsInRadius() {
    let userLocation = SessionDAO.getUserPosition();
    this.productService.listProductsInRadius(userLocation).then(
      response => {
        if (response.status === 200) {
          const task = response.entity;
          if (task.success) {
            this.graph = Object.assign(new Graph(), task.data);
            this.products = this.graph.listAllProducts();
            this.populatePages(this.products);
            this.loading = false;
          } else {
            this.error = true;
            this.errorMessage = task.errorMessage;
          }
          if(task.errorCode == 123) {
            this.showInfo = true;
            this.infoMessage = 'Não foi possível estabelecer sua localização. Ela foi setada para o centro do Recife e pode ser alterada no mapa.';
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

  public filterByName() {
    if(this.nameFilter !== undefined) {
      let filteredProducts = [];
      for(let i = 0; i < this.products.length; i++) {
        if(this.products[i].name.toLowerCase().includes(this.nameFilter.toLowerCase())) {
          filteredProducts.push(this.products[i]);
        }
      }
      this.populatePages(filteredProducts);
    } else {
      this.populatePages(this.products);
    }
  }

  public updateFilter() {
    let distance =  this.distanceFilter / 1000;
   this.products =  this.graph.filterByCategorysAndDistance(this.categoryFilter, distance);
   this.filterByName();
  }

  public closeInfo() {
    this.showInfo = false;
    this.infoMessage = null;
  }

  public closeError() {
    this.error = false;
    this.errorMessage = null;
  }
}
