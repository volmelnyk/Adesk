package com.adesk.service.implementation;

import com.adesk.dao.SubCategoryDao;
import com.adesk.models.SubCategory;
import com.adesk.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCatagoryServiceImpl implements SubCategoryService {
    @Autowired
    SubCategoryDao subCategoryDao;

    @Override
    public void save(SubCategory subCategory) {
        subCategoryDao.save(subCategory);
    }

    @Override
    public void delete(int id) {
        subCategoryDao.deleteById(id);
    }

    @Override
    public List<SubCategory> findAll() {
        return subCategoryDao.findAll();
    }
}
