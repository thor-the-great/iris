import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { SubscribeComponent } from './subscribe/subscribe.component';
import { HttpClientModule } from '@angular/common/http';

import { SubscriberService } from './shared/subscriber/subscriber.service';

const appRoutes: Routes = [
  {
    path: 'subscribe-new', component: SubscribeComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    SubscribeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [SubscriberService],
  bootstrap: [AppComponent]
})
export class AppModule { }
