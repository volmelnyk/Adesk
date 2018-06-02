package com.adesk.service.implementation;

import com.adesk.dao.CategoryDao;
import com.adesk.models.Category;
import com.adesk.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatagoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void delete(int id) {
        categoryDao.deleteById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
