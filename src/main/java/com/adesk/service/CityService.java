package com.adesk.service;

import com.adesk.models.City;

import java.util.List;

public interface CityService {

    void delete(int id);
    void save(City city);
    City findById(int id);
    City findByName(String name);
    List<City> findAllCities();
}
