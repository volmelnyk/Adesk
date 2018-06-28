import { Component, OnInit } from '@angular/core';
import {ErrorService} from "../../service/error.service";
import {AuthService} from "../../service/auth.service";
import {ImageLoadService} from "../../service/image-load.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import * as _ from "lodash";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  private currentUser: any;
  private defoultUser: any;
  private emailRegex: RegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  private usernameRegex: RegExp = /^[a-z0-9._-]+$/i;

  public userAlerts: any[];
  public phoneAlerts: any[];
  public passwordAlerts: any[];

  public cities: any;
  public initialCity: string;
  public phones: any
  public avatar: string;

  public password: any = {};
  public isPasswordDisabled: boolean = false;


  private selectedFiles: FileList;
  private currentFileUplouad: File;

  private newPhone;
  private photoAv = "./assets/avatar/";

  constructor(private http: HttpClient,
              private errorService: ErrorService,
              private authService: AuthService,
              private router: Router,
              private loadPhoto: ImageLoadService) {
  }

  ngOnInit() {
    console.log(this.authService.getUser());
    this.http.get<any>("http://localhost:8080/users/" + this.authService.getUser().sub).subscribe(
      data => {
        this.currentUser = data;
        this.defoultUser = data;
        this.phones = data.phones;
        this.photoAv = data.photo;
        console.log(this.photoAv);
        console.log(this.currentUser);

      },
      error => {
        this.errorService.handleError(error);
      },
      () => {
        this.http.get("http://localhost:8080/city/get-all").subscribe(
          (data: any) => {
            this.cities = data;
            this.initCity();
            console.log(this.cities);
            console.log(this.initialCity);
          }
        )
      }
    )
  }


  private saveUser() {
    if (this.validateUser()) {

      console.log("saveUser");
      console.log(this.currentUser);
      this.http.post("http://localhost:8080/users/update", {
        id: this.currentUser.id,
        email: this.currentUser.email,
        secondName: this.currentUser.secondName,
        firstName: this.currentUser.firstName,
        username: this.currentUser.username,
        city: this.currentUser.city,
        description: this.currentUser.description,
      }).subscribe();
    }
  }

  private validateUser(): boolean {
    var errorKeys = new Array<string>();

    if (!this.currentUser.email) {
      errorKeys.push("EmailRequired");
    } else if (!this.emailRegex.test(this.currentUser.email)) {
      errorKeys.push("IncorrectEmail");
    }

    if (!this.currentUser.username) {
      errorKeys.push("UserNameRequired");
    } else if (!this.usernameRegex.test(this.currentUser.username)) {
      errorKeys.push("IncorrectUserName");
    }

    if (errorKeys.length != 0) {
      this.userAlerts = this.errorService.getErrors(errorKeys);
      return false;
    }

    this.userAlerts = new Array<string>();

    return true;
  }

  savePassword() {
    this.isPasswordDisabled = true;

    if (this.validatePassword()) {
      this.http
        .post("http://localhost:8080/users/change-password", {
            newPassword: this.password.newPassword,
            oldPassword: this.password.oldPassword
          },
          {
            responseType: "text"
          })
        .subscribe(result => {
            console.log(result);
          },
          (error: any) => {
            console.log("2141324");
            console.log(error);
            this.passwordAlerts = this.errorService.handleError(error);
            this.password = {};
            this.isPasswordDisabled = false;
          },
          () => {
            this.password = {};
            this.isPasswordDisabled = false;
          })
    }
    else {
      this.isPasswordDisabled = false;
    }
  }

  validatePassword(): boolean {
    var errorKeys = new Array<string>();

    if (!this.password.oldPassword) {
      errorKeys.push("OldPasswordRequired");

    }
    else if (!this.password.newPassword) {
      errorKeys.push("NewPasswordRequired");
    } else if (this.password.newPassword.length < 8) {
      errorKeys.push("PasswordTooShort");
    }
    else if (!this.password.confirmPassword) {
      errorKeys.push("PasswordConfirmRequired");
    }
    else if (this.password.newPassword !== this.password.confirmPassword) {
      errorKeys.push("PasswordsDontMatch");
    }
    else if (this.password.newPassword !== this.password.confirmPassword) {
      errorKeys.push("PasswordsDontMatch");
    }

    if (errorKeys.length != 0) {
      this.passwordAlerts = this.errorService.getErrors(errorKeys);
      return false;
    }

    this.passwordAlerts = new Array<string>();

    return true;
  }

  private removeAlert(index, alerts) {
    alerts.splice(index, 1);
  }

  public initCity() {
    if (!this.currentUser || !this.cities) {
      return;
    }

    if (!this.currentUser.city.id) {
      this.initialCity = "";
      return;
    }

    var res = _.find(this.cities, (city: any) => {
      return city.id == this.currentUser.city.id;
    });

    if (res) {

      this.initialCity = res.name;
      return;
    }

    this.initialCity = "";
  }


  onSelect(cityId) {
    for (var i = 0; i < this.cities.length; i++) {
      if (this.cities[i].id == cityId) {
        this.currentUser.city = this.cities[i];
        this.initialCity = this.cities[i].name;
      }
    }
  }

  private resetUser() {

    this.currentUser = _.clone(this.defoultUser, true);
  }

  private addPhone() {
    console.log(this.newPhone);
    const credentials = {number: this.newPhone}
    this.http.post<any>("http://localhost:8080/phone/add-phone", credentials).subscribe(
      null,
      null,
      () => {
        this.loadPhones();
      }
    );
  }

  private deletePhone(id) {
    this.http.delete("http://localhost:8080/phone/delete-phone/" + id).subscribe(
      null,
      null,
      () => {
        this.loadPhones();
      }
    );
  }

  private loadPhones() {
    this.http.get("http://localhost:8080/phone/get-alll-pnones-by/" + this.currentUser.username).subscribe(
      data => {
        this.phones = data;
      }
    );
  }


  private selectedFile(event) {

    this.selectedFiles = event.target.files;
    this.upload();
  }

  public upload() {

    if (this.selectedFiles != undefined) {
      this.currentFileUplouad = this.selectedFiles.item(0);
      this.loadPhoto.pushFileToStorage(this.currentFileUplouad, "http://localhost:8080/users/upload-avatar").subscribe();

      this.selectedFiles = undefined;

      this.photoAv = this.currentUser.photoAv;
      // location.reload();
    }
  }

  public photoDefoult()
  {
    this.http.get("http://localhost:8080/users/change-to-defoult_photo").subscribe();
    location.reload();
  }

}
