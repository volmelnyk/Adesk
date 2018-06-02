package com.adesk.service.implementation;

import com.adesk.dao.AdvertDao;
import com.adesk.models.Advert;
import com.adesk.models.SubCategory;
import com.adesk.models.User;
import com.adesk.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Advert findById(int id) {

        return advertDao.getOne(id);
    }

    @Override
    public List<Advert> findByUser(User user) {
        return findByUser(user);
    }

    @Override
    public List<Advert> findAll() {
        return advertDao.findAll();
    }

    @Override
    public List<Advert> findBySubCategory(SubCategory subCategory) {
        return findBySubCategory(subCategory);
    }
}
