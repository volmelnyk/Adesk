package com.adesk.service;

import com.adesk.models.Advert;
import com.adesk.models.SubCategory;
import com.adesk.models.User;

import java.util.List;

public interface AdvertService {

    void delete(int id);
    void save(Advert advert);
    Advert findById(int id);
    List<Advert> findByUser(User user);
    List<Advert> findAll();
    List<Advert> findBySubCategory(SubCategory subCategory);
}
