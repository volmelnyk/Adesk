import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../service/auth.service";
import {daLocale} from "ngx-bootstrap";

@Component({
  selector: 'app-edit-advert',
  templateUrl: './edit-advert.component.html',
  styleUrls: ['./edit-advert.component.css']
})
export class EditAdvertComponent implements OnInit {

  private id: any;
  private advert: any;


  constructor(private route: ActivatedRoute, private http: HttpClient, private authService: AuthService) {
  }

  ngOnInit() {
    this.route.params.subscribe(
      params => {
        this.id = params['id'];

        this.http.get("http://localhost:8080/advert/" + this.id).subscribe(
          data => {
            this.advert = data;

            console.log(this.advert);
          }
        )
      }
    )
  }


}
