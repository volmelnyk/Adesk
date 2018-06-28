package com.adesk.DTO.response;

import com.adesk.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResponseDTO {
    private int id;
    private String text;
    private int receiverId;
    private Date date;
    private String senderName;
    private  String senderPhoto;

    public ResponseDTO(int id, String text, int receiverId, Date date, String senderName, String senderPhoto) {
        this.id = id;
        this.text = text;
        this.receiverId = receiverId;
        this.date = date;
        this.senderName = senderName;
        this.senderPhoto = senderPhoto;
    }
}
