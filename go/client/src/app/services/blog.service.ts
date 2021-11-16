import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Response } from '../models/response';
import { Blog } from '../models/Blog';

@Injectable({
  providedIn: 'root',
})
export class BlogService {
  constructor(private httpClient: HttpClient) {}

  getBlogs(): Observable<Response<Blog>> {
    return this.httpClient.get<Response<Blog>>('/api/blogs');
  }
}
