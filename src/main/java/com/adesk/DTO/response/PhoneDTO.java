package com.adesk.DTO.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhoneDTO {
    private String number;

    public PhoneDTO(String number) {
        this.number = number;
    }
}
