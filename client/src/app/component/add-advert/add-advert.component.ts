import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ImageLoadService} from "../../service/image-load.service";
import {ErrorService} from "../../service/error.service";
import {AuthService} from "../../service/auth.service";


@Component({
  selector: 'app-add-advert',
  templateUrl: './add-advert.component.html',
  styleUrls: ['./add-advert.component.css']
})
export class AddAdvertComponent implements OnInit {

  private categories: any;

  private title: string;
  private selectCategory: any;
  private descripton: any;
  private photo = "nophoto.jpg";
  private price: any;
  private selectedFiles: any;
  private currentPhoto: any;

  private photoAdverts: string;
  private currentFileUplouad: any;

  private userAlerts: any;

  constructor(private http: HttpClient,private authService: AuthService,private loadPhoto: ImageLoadService, private errorService: ErrorService) {
  }

  ngOnInit() {
    this.http.get<any>("http://localhost:8080/category/all").subscribe(data => {
      this.categories = data;
    });
  }

  onSelect(id) {
    console.log(id);
    this.selectCategory = id;
    console.log(this.selectCategory);
  }

  validateAdvert(): boolean {

    var errorKeys = new Array<string>();
    //
    if (!this.title) {
      errorKeys.push("TitleRequired");
    }
    if (!this.selectCategory) {
      errorKeys.push("CategoryRequired")
    }
    if (!this.price) {
      errorKeys.push("PriceRequired")
    } else if (this.price < 0) {
      errorKeys.push("NegativePrice")
    }
    if (errorKeys.length != 0) {
      this.userAlerts = this.errorService.getErrors(errorKeys);
      console.log(this.userAlerts)
      return false;
    }

    console.log(this.userAlerts);
    this.userAlerts = new Array<string>();

    return true;
  }

  removeAlert(index, alerts) {
    alerts.splice(index, 1);
  }

  save() {

    if (this.validateAdvert())
      this.http.post("http://localhost:8080/advert/add", {
        title: this.title,
        price: this.price,
        descripton: this.descripton,
        categoryId: this.selectCategory,
        photo: this.photo,

      }).subscribe(null, error => {

        },
        () => {
          console.log("saved");
        });
  }

  public selectedFile(event) {

    this.selectedFiles = event.target.files;
    this.upload();
    console.log(this.photo);
  }

  public photoDefoult() {
    this.http.get("http://localhost:8080/advert/delete-upload-photo").subscribe(
    );
    this.photo = "nophoto.jpg";
  }

  public upload() {

    if (this.selectedFiles != undefined) {
      this.currentFileUplouad = this.selectedFiles.item(0);
      this.currentPhoto = this.currentFileUplouad.name;
      console.log(this.photo);
      const formdata: FormData = new FormData();
      formdata.append('file', this.currentFileUplouad);
      this.http.post("http://localhost:8080/advert/upload-photo", formdata).subscribe(
        data => {
          this.currentPhoto = data;
          this.photo = this.currentPhoto.photo;
        }
      )

      this.selectedFiles = undefined;


    }
  }


}
