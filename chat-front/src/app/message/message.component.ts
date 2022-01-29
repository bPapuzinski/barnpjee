import { HttpClient } from '@angular/common/http';
import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import { Subscription } from 'rxjs';
import { of } from 'rxjs/internal/observable/of';
import { timer } from 'rxjs/internal/observable/timer';
import {catchError, filter, switchMap} from 'rxjs/operators';
import { Message } from '../model/message';
import {MessagingService} from '../service/messaging.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit, OnDestroy {
  @Input() clientName: string;

  messages: Map<string, Message>;

  subscription: Subscription;
  seconds: number;

  constructor(private http: HttpClient,
              private messagingService: MessagingService) {}

  // tslint:disable-next-line:typedef
  ngOnInit() {
    this.seconds = 2 * 1000;

    // @ts-ignore
    this.subscription = timer(0, this.seconds)
    .pipe(
      switchMap(() => {
        return this.getMessages()
        .pipe(catchError(err => {
          // Handle errors
          console.error(err);
          return of(undefined);
        }));
      }),
      filter(data => data !== undefined)
    )
    .subscribe(data => {
      this.messages = data;
      console.log(this.messages);
    });
  }

  // tslint:disable-next-line:typedef
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  // tslint:disable-next-line:typedef
  getMessages() {
    return this.messagingService.getMessages(this.clientName);
  }


}
