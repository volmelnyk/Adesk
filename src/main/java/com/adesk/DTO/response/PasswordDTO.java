package com.adesk.DTO.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PasswordDTO {
    public String oldPassword;
    public String newPassword;
}
