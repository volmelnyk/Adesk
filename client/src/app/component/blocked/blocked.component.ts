import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-blocked',
  templateUrl: './blocked.component.html',
  styleUrls: ['./blocked.component.css']
})
export class BlockedComponent implements OnInit {
  private authService: AuthService;

  constructor(authService: AuthService) {
    this.authService = authService;
  }

  ngOnInit() {
  }

  public isNonLocked() {
     return !this.authService.isNonBlock();
}
}
