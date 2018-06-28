package com.adesk.service;

import com.adesk.DTO.response.SubCategoryDTO;
import com.adesk.models.Category;
import com.adesk.models.SubCategory;

import java.util.List;

public interface SubCategoryService {
    void save(SubCategory category);
    void delete(int id);
    SubCategory findById(int id);
    List<SubCategoryDTO> findAll();
}
