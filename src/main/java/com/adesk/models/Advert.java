package com.adesk.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String Title;
    private Date date;
    private float price;
    private String description;
    private  String photo;

    @ManyToOne
    private User user;

    @ManyToOne
    private SubCategory subCategory;

    public Advert(String title, Date date, float price, String description, User user, SubCategory subCategory, String photo) {
        Title = title;
        this.date = date;
        this.price = price;
        this.description = description;
        this.user = user;
        this.subCategory = subCategory;
        this.photo = photo;
    }
}
