import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SmsService {

  constructor() { }

  smsGonder(){
    return "sms gönderildi";
  }
}
