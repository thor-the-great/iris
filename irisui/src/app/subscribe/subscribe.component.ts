import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import { SubscriberService } from '../shared/subscriber/subscriber.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-subscribe',
  templateUrl: './subscribe.component.html',
  styleUrls: ['./subscribe.component.css']
})
export class SubscribeComponent implements OnInit {

  url: string;

  constructor(private subscriberService: SubscriberService) {
  }

  ngOnInit() {
  }

  subscribe() {
    this.subscriberService.subscribe(this.url).subscribe(
      response => console.log(response),
      err => console.log(err)
    );
  }
}
