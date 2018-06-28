package com.adesk.controller;

import com.adesk.DTO.response.SubCategoryDTO;
import com.adesk.service.SubCategoryService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/category/")
public class CatagoryController {
    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("all")
    List<SubCategoryDTO> getAll()
    {
        return subCategoryService.findAll();
    }
}
