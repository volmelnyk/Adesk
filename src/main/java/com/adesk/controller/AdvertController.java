package com.adesk.controller;

import com.adesk.models.Advert;
import com.adesk.models.User;
import com.adesk.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advert")
public class AdvertController {
    @Autowired
    private AdvertService advertService;

    @GetMapping("/add")
    void AddAdvert(@RequestBody Advert newAdvert)
    {
        advertService.save(newAdvert);
    }
    @PostMapping("/getAll")
    List<Advert> getAll()
    {
        return advertService.findAll();
    }
    @GetMapping("getAllByUser")
    List<Advert> getAllByUser(@RequestBody User user)
    {
        return advertService.findByUser(user);
    }
    @GetMapping("getAllById")
    Advert getAllByUser(@RequestBody int id)
    {
        return advertService.findById(id);
    }
}
