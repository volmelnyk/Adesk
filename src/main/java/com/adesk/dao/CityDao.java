package com.adesk.dao;

import com.adesk.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City,Integer> {

    City getByName(String name);
}
