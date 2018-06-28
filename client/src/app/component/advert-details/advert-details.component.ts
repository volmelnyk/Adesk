import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-advert-details',
  templateUrl: './advert-details.component.html',
  styleUrls: ['./advert-details.component.css']
})
export class AdvertDetailsComponent implements OnInit {

  private currentAdvertId: any;
  private advert: any;
  private phones: any;


  constructor(private route: ActivatedRoute, private http: HttpClient, private authService: AuthService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {

      this.currentAdvertId = params['id'];

      this.http.get("http://localhost:8080/advert/" + this.currentAdvertId).subscribe(
        data => {
          this.advert = data;
          console.log(data);
        }, null, () => {
          this.http.get("http://localhost:8080/phone/get-all-pnones-by/" + this.advert.username).subscribe(
            data => {
              this.phones = data;
            }
          );
        }
      );
    });
  }

  isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  role() {
    return this.authService.getUser().role;
  }

}
