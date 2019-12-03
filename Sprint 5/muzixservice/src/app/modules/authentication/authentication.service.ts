import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
export const TOKEN_NAME= 'jwt_token';
export const USER_NAME = 'username';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private SpringRegisterEndPoint: string;
  private SpringSaveEndPoint: string;
  private SpringLoginEndPoint: string;

   constructor(private httpclient: HttpClient) {
     // this.SpringRegisterEndPoint = 'http://localhost:8085/api/v1/usertrackservice/'; //s5
    this.SpringRegisterEndPoint = 'http://localhost:8086/orchestrationservice/api/v1/user/'; //S5
    this.SpringLoginEndPoint = 'http://localhost:8086/authenticationservice/api/v1/userservice/login'; //s5
    // this.SpringSaveEndPoint = 'http://localhost:8084/api/v1/userservice/'; //s5

   }

  registerUser(newUser){
    // const url = this.SpringRegisterEndPoint + 'register'; //s5
    const url = this.SpringRegisterEndPoint ;
    return this.httpclient.post(url, newUser, {observe: 'response'});
  }

  // saveUser(newUser){
  //   const url = this.SpringSaveEndPoint + 'save';
  //   return this.httpclient.post(url, newUser);
  // }

  loginUser(newUser){
    // const url = this.SpringRegisterEndPoint + 'login'; //s5
    const url = this.SpringLoginEndPoint; //s5
    sessionStorage.setItem(USER_NAME, newUser.username);
    return this.httpclient.post(url, newUser, {observe: 'response'});
  }

  getToken() {
    return localStorage.getItem(TOKEN_NAME);
  }

  isTokenExpired(token?: string): boolean {
    if (localStorage.getItem(TOKEN_NAME)) {
      return true;
    } else {
      return false;
    }
  }

  logout() {
    sessionStorage.removeItem(USER_NAME);
    sessionStorage.clear();
    localStorage.removeItem(TOKEN_NAME);
    sessionStorage.clear();
    console.log('logged out');
  }
}
