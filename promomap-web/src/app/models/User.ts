import { Marker } from './Marker';

export class User {

  private superId: string;
  private firstName: string;
  private lastName: string;
  private password: string;
  private email: string;
  private phone: string;
  private gender: any;
  private lastLocation = new Marker();

  public getSuperId(): string {
    return this.superId;
  }

  public getFirstName(): any {
    return this.firstName;
  }

  public setFirstName(firstName: string): void {
    this.firstName = firstName;
  }

  public getLastName(): any {
    return this.lastName;
  }

  public setLastName(lastName: string): void {
    this.lastName = lastName;
  }

  public setPassword(password: string): void {
    this.password = password;
  }

  public getPassword(): string {
    return this.password;
  }

  public setEmail(email: string): void {
    this.email = email;
  }

  public getEmail(): any {
    return this.email;
  }
  
  public setPhone(phone: string): void {
    this.phone = phone;
  }

  public getPhone(): any {
    return this.phone;
  }
  
  public setLastLocation(lastLocation: Marker): void {
    this.lastLocation = lastLocation;
  }

  public getLastLocation(): Marker {
    return this.lastLocation;
  }
  
  public setGender(gender: any): void {
    this.gender = gender;
  }

  public getGender(): any {
    return this.gender;
  }
  
  public equals(user: User): boolean {
    if(this.firstName == user.firstName
      && this.lastName == user.lastName
      && this.email == user.email
      && this.phone == user.phone
      && this.password == user.password
      && this.superId == user.superId
      && this.gender == user.gender) {
      return true;
    } else {
      return false;
    }
  }
  
}
