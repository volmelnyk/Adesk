import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {augmentAppWithServiceWorker} from "@angular/cli/utilities/service-worker";
import {JwtHelper} from "angular2-jwt";
import {JwtHelperService} from "@auth0/angular-jwt";
import {HttpClient} from "@angular/common/http";
import {Router, RouterModule} from "@angular/router";
import {ErrorService} from "../../service/error.service";

class httpService {
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  // private username: string;
  // private password: string;
  // private email: string;
  // private confirmPassword: string;

  public alerts: string[];
  public isDisabled: boolean = false;

  private emailRegex: RegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  private usernameRegex: RegExp = /^[a-z0-9._-]+$/i;

  public user = {
    email: "",
    username: "",
    secondName: "",
    firstName: "",
    password: "",
    confirmPassword: ""
  };

  constructor(private router: Router, private auth: AuthService, private errorService:ErrorService ) {

  }

  ngOnInit() {
  }

  register() {

    if (this.validate()) {

    this.auth.regiserUser(this.user.firstName, this.user.secondName, this.user.username, this.user.email, this.user.password);
  }
  }

  public validate(): boolean {
    this.alerts = new Array<string>();

    var errorKeys = new Array<string>();

    if (!this.user.email) {
      errorKeys.push("EmailRequired");
    } else if (!this.emailRegex.test(this.user.email)) {
      errorKeys.push("IncorrectEmail");
    }

    if (!this.user.username) {
      errorKeys.push("UserNameRequired");
    } else if (!this.usernameRegex.test(this.user.username)) {
      errorKeys.push("IncorrectUserName");
    }

    if (!this.user.password) {
      errorKeys.push("PasswordRequired");
    } else if (this.user.password.length < 8) {
      errorKeys.push("PasswordTooShort");
    }
    else if (!this.user.confirmPassword) {
      errorKeys.push("PasswordConfirmRequired");
    }
    else if (this.user.password !== this.user.confirmPassword) {
      errorKeys.push("PasswordsDontMatch");
    }

    if (errorKeys.length != 0) {
      this.alerts = this.errorService.getErrors(errorKeys);
      return false;
    }

    this.alerts = new Array<string>();

    return true;
  }

  public removeAlert(index) {
    this.alerts.splice(index, 1);
  }
}
