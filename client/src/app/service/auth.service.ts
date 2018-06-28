import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/map';
import {JwtHelperService} from "@auth0/angular-jwt";
import {Router} from "@angular/router";


@Injectable()
export class AuthService {
  private http: HttpClient;
  private jwtHelper: JwtHelperService;
  // public userInfo: any;


  constructor(http: HttpClient, jwtHelper: JwtHelperService, private router:Router) {
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
    // location.reload();
  }
  public logIn(ussername: string, password: string):Observable<void>
  {
    this.logOut()
     return this.getToken(ussername,password)
       .map(this.setToken,this);


  }

  setToken(tokrnObj: string)
  {
   localStorage.setItem("token",tokrnObj);
    // this.setUserInfo(tokrnObj);
  }
  // private setUserInfo(accessToken) {
  //
  //    var parsed = this.jwtHelper.decodeToken(accessToken)
  //     console.log(parsed);
  //
  // }

  public getUser()
  {
    return this.jwtHelper.decodeToken(localStorage.getItem('token'));
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

  isNonLocked():boolean
  {
    if(this.isLoggedIn())
      return this.getUser().isNonLocked;
  }

  isNonBlock():boolean
  {
    return this.isNonLocked();
  }

  regiser(username:string,email:string,password:string,route:string)
  {
    console.log('http://localhost:8080/users/'+ route);
    this.http.post<any>('http://localhost:8080/users/'+route, {

      username:username,
      email:email,
      password:password
    }).subscribe(null,null,()=>{
      this.router.navigate(["login"]);});
  }

}
