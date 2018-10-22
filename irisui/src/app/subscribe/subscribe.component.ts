import { Component, OnInit } from '@angular/core';
import { Router} from "@angular/router";
import { SubscriberService } from '../shared/subscriber/subscriber.service';
import { HttpClientModule } from '@angular/common/http';
import { ConfigService} from '../shared/config.service';

@Component({
  selector: 'app-subscribe',
  templateUrl: './subscribe.component.html',
  styleUrls: ['./subscribe.component.css']
})
export class SubscribeComponent implements OnInit {

  url: string;
  email: string;
  subscribed : boolean = false;

  constructor(private subscriberService: SubscriberService, public configService:ConfigService) {
  }

  ngOnInit() {
    this.configService.getJSON().subscribe(data => {
      localStorage.setItem("serverPath", data["serverPath"]);
    });
  }

  subscribe() {
    this.subscriberService.subscribe(this.url, this.email).subscribe(
      response => {
        console.log(response);
        this.subscribed = true;
      },
      err => console.log(err)
    );
  }
}
