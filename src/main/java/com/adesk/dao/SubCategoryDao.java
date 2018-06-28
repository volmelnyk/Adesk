package com.adesk.dao;

import com.adesk.models.Category;
import com.adesk.models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryDao extends JpaRepository<SubCategory,Integer> {

    SubCategory findById(int id);
    List<SubCategory> findAll();

}
