import {Component, OnInit} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private categories: any;
  private adverts: any;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.http.get("http://localhost:8080/advert/getAll").subscribe(
      data => {
        this.adverts = data;

      }
      , null, () => {
        this.http.get<any>("http://localhost:8080/category/all").subscribe(data => {
          this.categories = data;
        });
      }
    );

  }

  submitFilter() {
    console.log("Filter");
  }

  showNotFound() {
    // if(this.adverts == undefined)
    // {
    //   return true
    // }
    // return false;
  }

}
