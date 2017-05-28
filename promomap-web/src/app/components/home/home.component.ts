import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private pages = [];
  private currentPage= 0;
  private products = [
    {imagePublicId: "l4jezlbbxyjd7fn0rp9n.jpg", name: "Açaí", price: 11},
    {imagePublicId: "gi6n7pgank4zur3yfg5g.jpg", name: "Skyline", price: 50000},
    {imagePublicId: "l4jezlbbxyjd7fn0rp9n.jpg", name: "Açaí", price: 11},
    {imagePublicId: "gi6n7pgank4zur3yfg5g.jpg", name: "Skyline", price: 50000},
    {imagePublicId: "l4jezlbbxyjd7fn0rp9n.jpg", name: "Açaí", price: 11},
    {imagePublicId: "gi6n7pgank4zur3yfg5g.jpg", name: "Skyline", price: 50000},
    {imagePublicId: "l4jezlbbxyjd7fn0rp9n.jpg", name: "Açaí", price: 11},
    {imagePublicId: "gi6n7pgank4zur3yfg5g.jpg", name: "Skyline", price: 50000},
    {imagePublicId: "l4jezlbbxyjd7fn0rp9n.jpg", name: "Açaí", price: 11},
    {imagePublicId: "gi6n7pgank4zur3yfg5g.jpg", name: "Skyline", price: 50000},
    {imagePublicId: "l4jezlbbxyjd7fn0rp9n.jpg", name: "Açaí", price: 11},
    {imagePublicId: "gi6n7pgank4zur3yfg5g.jpg", name: "Skyline", price: 50000},
    {imagePublicId: "l4jezlbbxyjd7fn0rp9n.jpg", name: "Açaí", price: 11},
    {imagePublicId: "gi6n7pgank4zur3yfg5g.jpg", name: "Skyline", price: 50000},
    {imagePublicId: "l4jezlbbxyjd7fn0rp9n.jpg", name: "Açaí", price: 11},
    {imagePublicId: "gi6n7pgank4zur3yfg5g.jpg", name: "Skyline", price: 50000},
    {imagePublicId: "l4jezlbbxyjd7fn0rp9n.jpg", name: "Açaí", price: 11},
    {imagePublicId: "gi6n7pgank4zur3yfg5g.jpg", name: "Skyline", price: 50000},
    {imagePublicId: "l4jezlbbxyjd7fn0rp9n.jpg", name: "Açaí", price: 11},
    {imagePublicId: "gi6n7pgank4zur3yfg5g.jpg", name: "Skyline", price: 50000},
    {imagePublicId: "l4jezlbbxyjd7fn0rp9n.jpg", name: "Açaí", price: 11},
    {imagePublicId: "gi6n7pgank4zur3yfg5g.jpg", name: "Skyline", price: 50000},
    {imagePublicId: "l4jezlbbxyjd7fn0rp9n.jpg", name: "Açaí", price: 11},
    {imagePublicId: "gi6n7pgank4zur3yfg5g.jpg", name: "Skyline", price: 50000},
    {imagePublicId: "l4jezlbbxyjd7fn0rp9n.jpg", name: "Açaí", price: 11},
    {imagePublicId: "gi6n7pgank4zur3yfg5g.jpg", name: "Skyline", price: 50000},
    {imagePublicId: "l4jezlbbxyjd7fn0rp9n.jpg", name: "Açaí", price: 11},
    {imagePublicId: "gi6n7pgank4zur3yfg5g.jpg", name: "Skyline", price: 50000},
    {imagePublicId: "l4jezlbbxyjd7fn0rp9n.jpg", name: "Açaí", price: 11},
    {imagePublicId: "gi6n7pgank4zur3yfg5g.jpg", name: "Skyline", price: 50000},
  ];

  ngOnInit() {
  	this.populatePages();
  }

  public populatePages() {
    let length = this.products.length;
    let productIndex = 0;
    let pageIndex = 0;
    while(length > productIndex) {
      let pageProducts = [];
      for(let i = productIndex; i < length; i++) {
        pageProducts.push(this.products[i]);
        productIndex++;
        if(productIndex % 24 == 0) {
          break;
        }
      }
      this.pages.push({index: pageIndex, products: pageProducts});
      pageIndex++;
    }
  }
}
