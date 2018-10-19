import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers : new HttpHeaders({'Content-Type' : 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class SubscriberService {

  constructor(private http: HttpClient) {
  }

  subscribe(subcribeUrl: string) {
    console.log('url : ' + subcribeUrl);
    var serviceUrl = 'http://localhost:8080/subscribeNew';
    const body = JSON.stringify({url : 'test.com'});
    console.log('serviceUrl : ' + serviceUrl);
    return this.http.post(serviceUrl, body, httpOptions);
  }
}
