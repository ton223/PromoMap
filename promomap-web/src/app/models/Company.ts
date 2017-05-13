import { Marker } from './Marker';

export class Company {
  private name: string;
  private description: string;
  private cnpj: string;
  private phone: string;
  private email: string;
  private location = new Marker();

  public setName(name: string): void {
    this.name = name;
  }

  public getName(): string {
    return this.name;
  }

  public setLocation(marker: Marker): void {
    this.location = marker;
  }

  public getLocation(): Marker {
    return this.location;
  }

  public setCnpj(cnpj: string): void {
    this.cnpj = cnpj;
  }

  public getCnpj(): string {
    return this.cnpj;
  }

  public setPhone(phone: string): void {
    this.phone = phone;
  }

  public getPhone(): string {
    return this.phone;
  }

  public setEmail(email: string): void {
    this.email = email;
  }

  public getEmail(): string {
    return this.email;
  }
}
