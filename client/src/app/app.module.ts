import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {JwtModule} from "@auth0/angular-jwt";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {AlertsModule, AlertsService} from "angular-alert-module";
import {NgHttpLoaderModule} from "ng-http-loader/ng-http-loader.module";
import {SelectModule} from "ng2-select";
import {AlertComponent, CollapseModule, TabsModule} from "ngx-bootstrap";

import {AuthService} from "./service/auth.service";
import {ErrorService} from "./service/error.service";
import {LoginComponent} from './component/login/login.component';
import {RegisterComponent} from './component/register/register.component';
import {JwtHelperService} from "@auth0/angular-jwt";
import {RouterModule, Routes} from "@angular/router";
import {FormsModule} from "@angular/forms";
import { BlockedComponent } from './component/blocked/blocked.component';
import { HeaderComponent } from './component/header/header.component';
import {register} from "ts-node";
import { UserProfileComponent } from './component/user-profile/user-profile.component';
import { HomeComponent } from './component/home/home.component';
import { AddAdvertComponent } from './component/add-advert/add-advert.component';
import { EditAdvertComponent } from './component/edit-advert/edit-advert.component';
import {ImageLoadService} from "./service/image-load.service";

export function tokenGetter() {
  return localStorage.getItem('token');
}

const routes: Routes = [
  {path:"", component:LoginComponent},
  {path:"register", component:RegisterComponent},
  {path:"login", component: LoginComponent},
  {path:'home', component: HomeComponent},
  {path:'user-profile', component:UserProfileComponent},
  {path:'user-profile/:username', component:UserProfileComponent},
  {path:'edit-user', component:EditAdvertComponent}
  ]

@NgModule({
  declarations: [
    AlertComponent,
    AppComponent,
    LoginComponent,
    RegisterComponent,
    BlockedComponent,
    HeaderComponent,
    UserProfileComponent,
    HomeComponent,
    AddAdvertComponent,
    EditAdvertComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AlertsModule.forRoot(),
    CollapseModule.forRoot(),
    NgHttpLoaderModule,
    SelectModule,
    FormsModule,
    TabsModule.forRoot(),
    NgbModule.forRoot(),
    JwtModule.forRoot({
          config:{
            tokenGetter: tokenGetter,
            whitelistedDomains: ['localhost:8080']
          }
    }),
    RouterModule.forRoot(routes)
  ],
  providers: [
    ImageLoadService,
    AlertsService,
    AuthService,
    ErrorService,
    JwtHelperService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
