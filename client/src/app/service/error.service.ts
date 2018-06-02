import { Injectable } from '@angular/core';
import { environment } from "../../environments/environment";
import {Router} from "@angular/router";
import {forEach} from "@angular/router/src/utils/collection";

@Injectable()
export class ErrorService {
  private router: Router;
  private errorDict = {
    UserNotExist: "Невірний пароль чи логін"/*"Користувача з таким логіном не існує"*/,
    InvalidPassword: "Невірний пароль",
    EmailExist: "Користувач з такою електронною адресою вже існує",
    UserNameExist: "Користувач з таким логіном вже існує",
    IncorrectEmail: "Некорректна електронна адреса",
    IncorrectUserName: "Некорректне ім'я користувача",
    PasswordsDontMatch: "Паролі не співпадають",
    PasswordTooShort: "Пароль повинен містити щонайменше 8 символів",
    EmailRequired: "Електронна пошта обов'язкова",
    UserNameRequired: "Ім'я користувача обов'язкове",
    PasswordRequired: "Пароль обов'язковий",
    PasswordConfirmRequired: "Необхідно підтвердити пароль",
    UnknownError: "Ой, щось пішло не так",
    TitleRequired: "Заголовок обов'язковий",
    CategoryRequired: "Категорія обов'язкова",
    PriceRequired: "Ціна обов'язкова",
    NegativePrice: "Ціна не може бути від'ємною",
    PhoneDupe: "Телефон вже існує",
    OldPasswordRequired: "Старий пароль обов'язковий",
    NewPasswordRequired: "Новий пароль обов'язковий"
  };

  constructor(router: Router) {
    this.router = router;
  }

  public getErrors(keys: Array<string>): Array<string> {
    var errors = new Array<string>();
    keys.forEach(val => {
      if(this.errorDict[val] === undefined) {
        errors.push(this.errorDict["UnknownError"]);
      }
      else {
        errors.push(this.errorDict[val]);
      }
    });
    return errors;
  }

  public handleError(errorResponse): Array<string> {
    if (!environment.production) {
      console.log(errorResponse)
    };

    if (errorResponse.status == 401 || errorResponse.status == 403) {
      this.router.navigate(["unauthorized"]);
      return;
    }


    var error;
    if (typeof errorResponse.error === "string") {
      console.log(errorResponse.error);
      return this.getErrors([errorResponse.error])
    }
    else
    {
      error = errorResponse.error;
    }
    if (error.ErrorCodes) {
      return this.getErrors(error.ErrorCodes);
    }

    return this.getErrors(["UnknownError"]);
  }
}
