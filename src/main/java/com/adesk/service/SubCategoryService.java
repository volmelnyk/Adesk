package com.adesk.service;

import com.adesk.models.SubCategory;

import java.util.List;

public interface SubCategoryService {
    void save(SubCategory category);
    void delete(int id);
    List<SubCategory> findAll();
}
