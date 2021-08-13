import { Component, OnInit } from '@angular/core';
import { SmsService } from '../services/sms.service';

@Component({
  selector: 'app-hakkinda',
  templateUrl: './hakkinda.component.html',
  styleUrls: ['./hakkinda.component.css'],
})
export class HakkindaComponent implements OnInit {
  public sirketAdi: String = 'İmovasyon';

  constructor(private smsService: SmsService) {}

  ngOnInit(): void {}

  smsGonderici(event: any) {
    this.smsService.smsGonder();

    console.log('sms gönderildi', event);
  }
}
