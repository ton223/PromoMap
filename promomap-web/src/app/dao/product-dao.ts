import { Product } from '../models/Product';

export class productDAO {
    private static products = [];
    private static currentProduct: any;

    public static getProducts(): Product[] {
        return this.products;
    }

    public static addProducts(products: Product[]) {
        this.clear();
        for(let i = 0; i < products.length; i++) {
            this.products.push(products[i]);
        }
    }

    public static clear() {
        let length = this.products.length;
        for(let i = 0; i < length; i++) {
            this.products.pop();
        }
    }

    public static remove(product: Product) {
        let index = this.products.indexOf(product);
        if(index != -1) {
            this.products.splice(index, 1);
        }
    }

    public static add(product: Product) {
        this.products.push(product);
    }

    public static setCurrentProduct(cp: any) {
        this.currentProduct = cp;
    }

    public static getCurrentProduct(): any {
        return this.currentProduct;
    }
}