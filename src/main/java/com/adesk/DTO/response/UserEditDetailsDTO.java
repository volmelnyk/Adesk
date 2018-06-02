package com.adesk.DTO.response;

import com.adesk.models.Advert;
import com.adesk.models.Phone;
import com.adesk.models.Response;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserEditDetailsDTO {

    public String username;
    public String email;
    public String descride;
    public String photo;
    public String firstName;
    public String secondName;
    public String password;

    public List<Phone> phones;
    public  List<Advert> adverts;
    public List<Response> responses;

    public UserEditDetailsDTO(String username, String email, String descride, String photo, String firstName, String secondName, String password, List<Phone> phones, List<Advert> adverts, List<Response> responses) {
        this.username = username;
        this.email = email;
        this.descride = descride;
        this.photo = photo;
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.phones = phones;
        this.adverts = adverts;
        this.responses = responses;
    }
}
