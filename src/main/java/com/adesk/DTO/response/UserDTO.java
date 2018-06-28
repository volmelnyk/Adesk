package com.adesk.DTO.response;


import com.adesk.models.City;
import com.adesk.models.Phone;
import com.adesk.models.Response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {

    private  int id;
    private String email;
    private String firstName;
    private String secondName;

    private String username;
//    private String password;

    private String photo;
    private City city;
    private  boolean isAccountNonLocked;

    List<Phone> phones;

//    List<Response> responseList

    public UserDTO(int id, String email, String firstName, String secondName, String username,boolean isAccountNonLocked,String photo, City city) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.username = username;
//        this.password = password;
        this.isAccountNonLocked =isAccountNonLocked;
        this.photo = photo;
        this.city = city;
    }
    public UserDTO(int id,String email, String firstName, String secondName, String username, String photo, City city, boolean isAccountNonLocked, List<Phone> phones) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.username = username;
        this.photo = photo;
        this.city = city;
        this.isAccountNonLocked = isAccountNonLocked;
        this.phones = phones;
    }
}
