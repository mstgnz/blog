import { Comment } from './comment';

export class Blog {
  id: number = 0;
  slug: string = '';
  userId: number = 0;
  title: string = '';
  shortText: string = '';
  longText: string = '';
  createDate: Date = new Date();
  updateDate: Date = new Date();
  //comments: Comment[];
}
