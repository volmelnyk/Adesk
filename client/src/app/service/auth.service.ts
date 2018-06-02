import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/map';
import {JwtHelperService} from "@auth0/angular-jwt";


@Injectable()
export class AuthService {
  private http: HttpClient;
  private jwtHelper: JwtHelperService;
  public userInfo: any;


  constructor(http: HttpClient, jwtHelper: JwtHelperService) {
    this.http = http;
    this.jwtHelper = jwtHelper;
  }

  getToken(ussername: string, password: string): Observable<any> {
    const credentials = {username: ussername, password: password};
    console.log('attempAuth ::');
    return this.http.post<any>('http://localhost:8080/login', credentials);
  }

  public logOut()
  {
    localStorage.removeItem('token');
    // location.reload();
  }
  public logIn(ussername: string, password: string)/*:Observable<any>*/
  {
     this.getToken(ussername,password).subscribe(
      data=>
      {
        console.log(data);
        this.setToken(data.token);
      }
    )


  }

  setToken(tokrnObj: string)
  {
   localStorage.setItem("token",tokrnObj);
    this.setUserInfo(tokrnObj);
  }
  private setUserInfo(accessToken) {

     var parsed = this.jwtHelper.decodeToken(accessToken)
      console.log(parsed);

  }

  public getUser()
  {
    return this.jwtHelper.decodeToken(localStorage.getItem('token'));
  }

  public getUserInfo()
  {
    console.log(this.userInfo);
    return this.userInfo;
  }

  isLoggedIn():boolean
  {
    // location.reload();
    if(localStorage.getItem('token') === null)
    {
      return false;
    }
    // if(!Boolean(this.jwtHelper.is))
    // {
    //   return false;
    // }
    return true;
  }

  isNonLocked(){
    return this.jwtHelper.decodeToken(localStorage.getItem('token')).isNonLocked;
  }

  regiserUser(firstName: string, secondName: string,username:string,email:string,password:string)
  {

    this.http.post<any>('http://localhost:8080/users/signOut', {
      firstName:firstName,
      secondName:secondName,
      username:username,
      email:email,
      password:password
    }).subscribe();
  }
}
