export class Company {
  private name: string;

  public setName(name: string): void {
    this.name = name;
  }

  public getName(): string {
    return this.name;
  }
}
