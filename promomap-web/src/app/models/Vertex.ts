import { Product } from './Product';
import { Company } from './Company';

export class Vertex {

  private id: number;
  private type: any;
  private category: any;
  private company: Company;
  private products: Product[];


  public getId(): number {
    return this.id;
  }

  public getType(): any {
    return this.type;
  }

  public setType(type: any): void {
    this.type = type;
  }

  public getCategory(): any {
    return this.category;
  }

  public setCategory(category: any): void {
    this.category = category;
  }

  public getCompany(): Company {
    return this.company;
  }

  public setCompany(company: Company): void {
    this.company = company;
  }

  public setProducts(products: Product[]): void {
    this.products = products;
  }

  public getProducts(): Product[] {
    return this.products;
  }

}
