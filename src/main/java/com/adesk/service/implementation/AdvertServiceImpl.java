package com.adesk.service.implementation;

import com.adesk.DTO.response.AdvertDTO;
import com.adesk.dao.AdvertDao;
import com.adesk.models.Advert;
import com.adesk.models.SubCategory;
import com.adesk.models.User;
import com.adesk.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    AdvertDao advertDao;

    @Override
    public void delete(int id) {
        advertDao.deleteById(id);
    }

    @Override
    public void save(Advert advert) {
        advertDao.save(advert);
    }

    @Override
    public AdvertDTO findById(int id) {

        Advert advert =  advertDao.getOne(id);

        AdvertDTO advertDTO = new AdvertDTO(advert.getId(),advert.getTitle(),advert.getPrice(),advert.getDescription(),
                advert.getSubCategory().getId(),advert.getPhoto(),advert.getUser().getUsername(),
                advert.getDate(),advert.getUser().getCity().getName(),advert.getSubCategory().getName());
        return advertDTO;
    }

    @Override
    public List<AdvertDTO> findByUser(User user) {

        List<AdvertDTO> list = new ArrayList<>();

        List<Advert> adverts =  advertDao.findAllByUser(user);

        for (Advert advert :adverts) {

            list.add(new AdvertDTO(advert.getId(),advert.getTitle(),advert.getPrice(),advert.getDescription(),
                    advert.getSubCategory().getId(),advert.getPhoto(),advert.getUser().getUsername(),
                    advert.getDate(),advert.getUser().getCity().getName(),advert.getSubCategory().getName()));
        }
        return list;
    }

    @Override
    public List<AdvertDTO> findAll() {
        List<AdvertDTO> list = new ArrayList<>();

        List<Advert> adverts =  advertDao.findAll();

        for (Advert advert :adverts) {

            list.add(new AdvertDTO(advert.getId(),advert.getTitle(),advert.getPrice(),advert.getDescription(),
                    advert.getSubCategory().getId(),advert.getPhoto(),advert.getUser().getUsername(),
                    advert.getDate(),advert.getUser().getCity().getName(),advert.getSubCategory().getName()));
        }

        return list;
    }


}
