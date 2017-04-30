import { User } from '../models/User';

export class SessionDAO {

  public static hasToken(): boolean {
    const token = localStorage.getItem('token');
    if (token !== undefined && token != null && token !== 'null' && token.length > 0) {
      return true;
    } else {
      return false;
    }
  }

  public static setUser(user: User) {
    localStorage.setItem('user', JSON.stringify(user));
  }

  public static getUser(): User {
    return JSON.parse(localStorage.getItem('user'));
  }

  public static setToken(token: string) {
    localStorage.setItem('token', token);
  }

  public static getToken(): string {
    return localStorage.getItem('token');
  }

  public static clearSession(): void {
    localStorage.setItem('token', null);
    localStorage.setItem('firstName', null);
  }
}
