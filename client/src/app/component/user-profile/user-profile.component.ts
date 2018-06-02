import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  public user: any;
  private userId: any;
  private comment: string = "";
  private responseList: any;
  private noavatar = "noavatar.jpg";
  private photoAv = "";
  private username: any;

  constructor(private route: ActivatedRoute, private http: HttpClient, private authService: AuthService, /*private errorService: ErrorService*/) {
  }


  ngOnInit() {
    this.route.params.subscribe(params => {
        this.username = params["username"];
        this.http.get<any>("http://localhost:8080/users/" + this.username).subscribe(
          data => {
            this.user = data;
            this.userId = data.id;
            this.photoAv = data.photo;
            this.getAllResponses();
          }
        )
      }
    )
  }

  public isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  public userInfo() {
    return this.authService.getUser();
  }

  public isOwner() {
    return this.isLoggedIn() && this.userInfo().userId == this.userId;
  }

  public getAllResponses() {
    // /response/all
    this.http.get("http://localhost:8080/response/all/" + this.userId).subscribe(
      data => {
        console.log(data);
        this.responseList = data;
      }
    )
  }

  public addCompent() {
    console.log(this.comment);
    console.log(this.userId);
    this.http.post("http://localhost:8080/response/add", {
      text: this.comment,
      receiverId: this.userId,
    }).subscribe(null,null,()=>{
      this.getAllResponses();
    });

  }
}
