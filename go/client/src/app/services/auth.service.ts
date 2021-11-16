import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Login } from '../models/login';
import { Register } from '../models/register';

@Injectable()
export class AuthService {
  constructor(
    private httpClient: HttpClient,
    private jwtHelper: JwtHelperService
  ) {}

  authToken: any;
  decoded: any;

  login(login: Login) {
    this.httpClient.post('/api/login', login).subscribe((data) => {
      localStorage.setItem('token', data['token']);
      this.authToken = data['token'];
      this.decoded = this.jwtHelper.decodeToken(data['token']);
    });
  }

  register(register: Register) {
    this.httpClient.post('/api/register', register).subscribe((data) => {
      localStorage.setItem('token', data['token']);
      this.authToken = data['token'];
      this.decoded = this.jwtHelper.decodeToken(data['token']);
    });
  }

  isTokenExpired() {
    return this.jwtHelper.isTokenExpired(localStorage.getItem('token') ?? '');
  }

  getTokenExpirationDate() {
    this.jwtHelper.getTokenExpirationDate(localStorage.getItem('token') ?? '');
  }
}
