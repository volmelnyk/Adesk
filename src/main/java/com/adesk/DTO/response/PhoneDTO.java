package com.adesk.DTO.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhoneDTO {

    private  int id;
    private String number;

    public PhoneDTO(int id, String number) {
        this.id = id;
        this.number = number;
    }
}
