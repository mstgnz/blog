import { Blog } from './Blog';

export class User {
  firstName: string = '';
  lastname: string = '';
  email: string = '';
  password: string = '';
  createDate: Date = new Date();
  blogs: Blog[] = [];
}
