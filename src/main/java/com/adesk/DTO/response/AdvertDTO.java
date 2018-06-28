package com.adesk.DTO.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AdvertDTO {

    private int id;
    private String title;
    private float price;
    private String description;
    private int categoryId;
    private String photo;
    private String username;
    private Date date;
    private String city;
    private String categoryName;


    public AdvertDTO(int id, String title, float price, String description, int categoryId, String photo, String username, Date date, String city, String categoryName) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.photo = photo;
        this.username = username;
        this.date = date;
        this.city = city;
        this.categoryName = categoryName;
    }
}
