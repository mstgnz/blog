export class Response<T> {
  status: boolean = false;
  message: string = '';
  errors?: any;
  data?: T[];
}
