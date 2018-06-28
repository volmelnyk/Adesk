import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";
import {ErrorService} from "../../service/error.service";
import {JwtHelper} from "angular2-jwt";
import {JwtHelperService} from "@auth0/angular-jwt";

@Component({

  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private authService: AuthService;
  private router: Router;
  private errorService: ErrorService;

  public login: string;
  public password: string;
  public alerts: Array<string>;
  public isDisabled: boolean = false;

  constructor(private jwtHelper: JwtHelperService, authService: AuthService, router: Router, errorService: ErrorService) {
    this.authService = authService;
    this.router = router;
    this.errorService = errorService;
  }

  ngOnInit() {
    console.log(localStorage.getItem('token'));
  }

  public logIn() {

    this.authService.logOut();
    console.log(this.jwtHelper.decodeToken(localStorage.getItem('token')));
    // this.authService.logIn(this.login, this.password);

    this.authService.getToken(this.login,this.password).subscribe(
      data=>
      {
        this.authService.setToken(data.token);
        console.log(data);
      },
      (error)=>{
        if(error.status==401)
        {
          this.alerts = new Array<string>();
          this.alerts.push('Невірно вказаний логін або пароль!');
          console.log(this.alerts);
        }
     },
      () => {
      this.router.navigate(['home']);
    }
      );
  }

  public removeAlert(index) {
    this.alerts.splice(index, 1);
  }
}
