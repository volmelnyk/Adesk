package com.adesk.service.implementation;

import com.adesk.dao.CityDao;
import com.adesk.models.City;
import com.adesk.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityDao cityDao;

    @Override
    public void delete(int id) {
        cityDao.deleteById(id);
    }

    @Override
    public void save(City city) {
        cityDao.save(city);
    }

    @Override
    public City findById(int id) {
        return cityDao.getOne(id);
    }

    @Override
    public City findByName(String name) {
        return cityDao.getByName(name);
    }

    @Override
    public List<City> findAllCities() {
        return cityDao.findAll();
    }
}
