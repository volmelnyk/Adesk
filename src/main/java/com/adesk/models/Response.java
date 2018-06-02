package com.adesk.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.soap.Text;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@ToString
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;
    private Date date;
    private int receiverId;
    private String senderName;
    private String senderPhoto;

    @JsonIgnore
    @ManyToOne
    private User user;

//    @JsonGetter("sender")
//    public User getUser() {
//        return user;
//    }
}
