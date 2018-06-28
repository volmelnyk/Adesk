import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.css']
})
export class AdminProfileComponent implements OnInit {

  private adminInfo:any;
  private  allUsers:any;

  constructor(private auth:AuthService, private http:HttpClient) { }

  ngOnInit() {
    this.http.get<any>("http://localhost:8080/users/"+this.auth.getUser().sub).subscribe(
      data=> {
        this.adminInfo = data;
      })
    this.getAllUsers();
  }

  public getAllUsers()
  {
    this.http.get("http://localhost:8080/users").subscribe(
      data=> {
        this.allUsers = data;
        console.log(data);
      }
    );
  }

  public lock(username:any){
    console.log(username);
    this.http.get<any>("http://localhost:8080/users/block/"+username).subscribe(null,null,
      ()=>
      {
        this.getAllUsers();
      });

  }

}
