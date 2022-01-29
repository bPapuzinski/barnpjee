import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LoginRequest, LoginResponse} from '../model/login';
import {Message, MessageSendRequest } from '../model/message';

@Injectable({
  providedIn: 'root'
})
export class MessagingService {
  private clientName: string;

  constructor(private http: HttpClient) {
  }

  // tslint:disable-next-line:typedef
  login(loginRequest: LoginRequest) {
    return this.http.post(`/register`, loginRequest);
  }

  // tslint:disable-next-line:typedef
  sendMessage(message: MessageSendRequest) {
    message.fromClientName = this.getClientName();
    return this.http.post(`/message/send`, message);
  }

  // tslint:disable-next-line:typedef
  getMessages(clientName: string) {
    return this.http.get(`/message/${clientName}`);
  }

  setClientName(clientName: string): void {
    this.clientName = clientName;
  }

  getClientName(): string {
    return this.clientName;
  }

  // tslint:disable-next-line:typedef
  public async getMessagesForClient(clientName: string) {
    this.http.get(`/message/${clientName}`).subscribe(
      (response: Map<string, Message>) => {
        return response;
      },
      (err) => {
        console.log(err);
      });
  }
}
