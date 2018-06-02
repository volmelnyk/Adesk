package com.adesk.service;

import com.adesk.models.Category;

import java.util.List;

public interface CategoryService {

    void save(Category category);
    void delete(int id);
    List<Category> findAll();
}
