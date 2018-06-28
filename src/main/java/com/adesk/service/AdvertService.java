package com.adesk.service;

import com.adesk.DTO.response.AdvertDTO;
import com.adesk.models.Advert;
import com.adesk.models.SubCategory;
import com.adesk.models.User;

import java.util.List;

public interface AdvertService {

    void delete(int id);
    void save(Advert advert);
    AdvertDTO findById(int id);
    List<AdvertDTO> findByUser(User user);
    List<AdvertDTO> findAll();

}
