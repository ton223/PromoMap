
export class Request {
  private token: string;
  private data: any;

  public setToken(token: string): void {
    this.token = token;
  }

  public getToken(): string {
    return this.token;
  }

  public setData(data: any): void {
    this.data = data;
  }

  public getData(): any {
    return this.data;
  }
}
