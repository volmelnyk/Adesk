import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class ImageLoadService {

  constructor(private http:HttpClient) { }

  pushFileToStorage(file: File, path:string): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();
    formdata.append('file', file);
    console.log(path);
    const req = new HttpRequest('POST', path, formdata, {
        reportProgress: true,
        responseType: 'text'
      }
    );
    return this.http.request(req);
  }

}
