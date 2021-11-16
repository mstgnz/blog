import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-hakkinda',
  templateUrl: './hakkinda.component.html',
  styleUrls: ['./hakkinda.component.css'],
})
export class HakkindaComponent implements OnInit {
  public sirketAdi: String = 'İmovasyon';

  constructor() {}

  ngOnInit(): void {}


}
