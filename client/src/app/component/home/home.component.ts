import {Component, OnInit} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";
import {HttpClient} from "@angular/common/http";
import * as _ from 'lodash'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public categories: any;
  public adverts: any;
  public filteredAdverts: any;
  public searchText: string;
  public minPrice: number;
  public maxPrice: number;
  public sorting:string;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.http.get("http://localhost:8080/advert/getAll").subscribe(
      data => {
        this.adverts = data;
        this.filteredAdverts = this.adverts;
      }
      , null, () => {
        this.http.get<any>("http://localhost:8080/category/all").subscribe(data => {
          this.categories = data;
        });
      }
    );

  }

  submitFilter() {
    this.filters();
    this.sort();

  }
public filters()
{
  var res = this.adverts;

  if (this.searchText) {
    res = _.filter(res, (val) => {
      return val.title.toLowerCase().includes(this.searchText.toLowerCase());
    });
  }

  if (this.minPrice) {
    res = _.filter(res, (val) => {
      return val.Price >= this.minPrice;
    });
  }

  if (this.maxPrice) {
    res = _.filter(res, (val) => {
      return val.Price <= this.maxPrice;
    });
  }

  this.filteredAdverts = res;
}

  public sort() {
    switch (this.sorting) {
      case "new": {
        this.filteredAdverts = _.orderBy(this.filteredAdverts, (val) => {
          return val.Date;
        }, "desc");
        break;
      }
      case "cheap": {
        this.filteredAdverts = _.orderBy(this.filteredAdverts, (val) => {
          return val.Price;
        }, "asc");
        break;
      }
      case "exp": {
        this.filteredAdverts = _.orderBy(this.filteredAdverts, (val) => {
          return val.Price;
        }, "desc");
        break;
      }
      default: {
        this.filteredAdverts = _.orderBy(this.filteredAdverts, (val) => {
          return val.Date;
        }, "desc");
      }
    }
  }

}
