import { Marker } from './Marker';

export class Company {
  private superId: string;
  private name: string;
  private description: string;
  private cnpj: string;
  private phone: string;
  private email: string;
  private location = new Marker();

  public setSuperId(superId: string): void {
    this.superId = superId;
  }

  public getSuperId(): string {
    return this.superId;
  }

  public setName(name: string): void {
    this.name = name;
  }

  public getName(): string {
    return this.name;
  }

  public setDescription(description: string): void {
    this.description = description;
  }

  public getDescription(): string {
    return this.description;
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

  public equals(company: Company): boolean {
    if(company.getName() == this.getName()
      && company.getCnpj() == this.getCnpj()
      && company.getPhone() == this.getPhone()
      && company.getEmail() == this.getEmail()
      && company.getSuperId() == this.getSuperId()
      && company.getDescription() == this.getDescription()) {
      return true;
    } else {
      return false;
    }
  }
}
