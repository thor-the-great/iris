import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private configUrl = "assets/data/config.json";

  constructor(private http: HttpClient) { }

  public getJSON(): Observable<any> {
    return this.http.get(this.configUrl)
  }

  public getServerPath(){
    return localStorage.getItem('serverPath');
    //return 'http://localhost:8080';
  }
}
