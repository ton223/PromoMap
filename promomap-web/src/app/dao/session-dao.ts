import { User } from '../models/User';
import { Marker } from '../models/Marker';

export class SessionDAO {

  private static user: User;
  
  public static hasToken(): boolean {
    const token = localStorage.getItem('token');
    if (token !== undefined && token != null && token !== 'null' && token.length > 0 && token != 'undefined') {
      return true;
    } else {
      return false;
    }
  }

  public static setUser(user: User) {
    localStorage.setItem('user', JSON.stringify(user));
    this.user = Object.assign(new User(), user);
  }

  public static getUser(): User {
    if(this.user != undefined && this.user != null) {
      return this.user;
    } else {
      return JSON.parse(localStorage.getItem('user'));
    }
  }

  public static setToken(token: string) {
    localStorage.setItem('token', token);
  }

  public static getToken(): string {
    return localStorage.getItem('token');
  }

  public static clearSession(): void {
    localStorage.setItem('token', undefined);
    localStorage.setItem('user', undefined);
  }

  public static setUserPosition(pos: any) {
    localStorage.setItem('userLocation', JSON.stringify(pos));
  }

  public static getUserPosition(): any {
    const pos = JSON.parse(localStorage.getItem('userLocation'));
    return pos;
  }
}
