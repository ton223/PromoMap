
export class User {

  private superId: string;
  private firstName: string;
  private lastName: string;
  private password: string;
  private email: string;

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
}
