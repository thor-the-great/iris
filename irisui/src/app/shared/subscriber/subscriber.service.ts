import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ConfigService} from '../config.service';

const httpOptions = {
  headers : new HttpHeaders({'Content-Type' : 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class SubscriberService {

  serverPath : string;

  constructor(public configService: ConfigService, private http: HttpClient) {
    this.serverPath = this.configService.getServerPath();
    console.log('server path : ' + this.serverPath);
  }

  subscribe(subcribeUrl: string, subcriberEmail: string) {
    console.log('url : ' + subcribeUrl);
    var serviceUrl = this.serverPath+'/subscribeNew';
    const body = JSON.stringify({url : subcribeUrl, email : subcriberEmail});
    console.log('serviceUrl : ' + body);
    return this.http.post(serviceUrl, body, httpOptions);
  }
}
