import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserLogin } from '../user-login';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/login';
  }

  authenticate(userLogin : UserLogin) {
    return this.http.post<UserLogin>(this.usersUrl, userLogin);
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    return !(user === null)
  }

  logOut() {
    //sessionStorage.removeItem('username')
  }
}
