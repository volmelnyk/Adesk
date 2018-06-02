package com.adesk.controller;

import com.adesk.models.City;
import com.adesk.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/city/")
public class CityController {

    @Autowired
    CityService cityService;

    @PostMapping("add-city")
    void addCity(@RequestBody City city)
    {
        cityService.save(city);
    }
    @GetMapping("get-all")
    List<City> getAllCity()
    {
        return  cityService.findAllCities();
    }
    @GetMapping("get-by-name/{cityName}")
    City getByName(@PathVariable String cityName)
    {
        return cityService.findByName(cityName);
    }
}
