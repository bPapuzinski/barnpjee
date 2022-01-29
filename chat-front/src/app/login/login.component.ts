import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {MessagingService} from '../service/messaging.service';
import {LoginRequest, LoginResponse} from '../model/login';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginFormGroup: FormGroup;
  userName = new FormControl('', [Validators.required]);
  uri = new FormControl('', Validators.required);
  messageType = new FormControl('', Validators.required);

  constructor(private router: Router,
              private messagingService: MessagingService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.loginFormGroup = this.formBuilder.group({
      userName: this.userName,
      uri: this.uri,
      messageType: this.messageType
    });
  }

  login(): void {
    const loginRequest: LoginRequest = {
      clientPublicName: this.userName.value,
      clientURL: this.uri.value,
      messageType: this.messageType.value,
    };
    this.messagingService.login(loginRequest).subscribe(
      (response: LoginResponse) => {
        this.messagingService.setClientName(response.message);
        this.router.navigateByUrl('/chat');
      },
      (err) => {
        console.log(err);
      });
  }

}
