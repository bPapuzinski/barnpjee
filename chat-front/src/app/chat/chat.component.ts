import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {MessagingService} from '../service/messaging.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { MessageSendRequest } from '../model/message';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  messageFormGroup: FormGroup;
  toClientName = new FormControl('', [Validators.required]);
  message = new FormControl('', Validators.required);
  messageType = new FormControl('', Validators.required);

  constructor(private router: Router,
              private messagingService: MessagingService,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.reinitializeChatForm();
  }

  private reinitializeChatForm(): void {
    this.toClientName = new FormControl('', [Validators.required]);
    this.message = new FormControl('', Validators.required);
    this.messageType = new FormControl('', Validators.required);
    this.messageFormGroup = this.formBuilder.group({
      toClientName: this.toClientName,
      message: this.message,
      messageType: this.messageType
    });
  }

  sendMessage(): void {
    const messageRequest: MessageSendRequest = {
      toClientName: this.toClientName.value,
      message: this.message.value,
      messageType: this.messageType.value,
    };
    this.messagingService.sendMessage(messageRequest).subscribe(
      (response) => {
        console.log(response);
      },
      (err) => {
        console.log(err);
      });
  }

}
