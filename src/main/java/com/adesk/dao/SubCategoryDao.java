package com.adesk.dao;

import com.adesk.models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryDao extends JpaRepository<SubCategory,Integer> {
}
