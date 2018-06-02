import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../service/auth.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  private authService: AuthService;
  private router: Router;

  constructor(authService: AuthService, router: Router) {
    this.authService = authService;
    this.router = router;

  }

  ngOnInit() {
  }

  public getUserRole() {
    return this.authService.getUser().role;
  }

  private userInfo()
  {
    return this.authService.getUser();
  }

  public isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  public logOut() {
    this.authService.logOut();
    this.router.navigate(['home']);
  }
}
