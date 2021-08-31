import { Comment } from './comment';

export class Blog {
  id: number = 0;
  userId: number = 0;
  title: string = '';
  content: string = '';
  createDate: Date = new Date();
  updateDate: Date = new Date();
  //comments: Comment[];
}
