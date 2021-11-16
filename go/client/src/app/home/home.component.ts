import { Component, OnInit } from '@angular/core';
import { Blog } from '../models/Blog';
import { BlogService } from '../services/blog.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [BlogService],
})
export class HomeComponent implements OnInit {
  constructor(private blogService: BlogService) {}
  blogs?: Blog[];

  ngOnInit(): void {
    this.blogService.getBlogs().subscribe((data) => {
      this.blogs = data.data;
      console.log(this.blogs)
    });
  }
}
