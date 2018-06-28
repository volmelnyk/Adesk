package com.adesk.service.implementation;

import com.adesk.DTO.response.SubCategoryDTO;
import com.adesk.dao.SubCategoryDao;
import com.adesk.models.Category;
import com.adesk.models.SubCategory;
import com.adesk.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public SubCategory findById(int id) {
        return subCategoryDao.findById(id);
    }

    @Override
    public List<SubCategoryDTO> findAll() {

        List<SubCategoryDTO> list = new ArrayList<>();
        for (SubCategory subCategory : subCategoryDao.findAll()) {
            list.add(new SubCategoryDTO(subCategory.getId(),subCategory.getName()));
        }
        return list;
    }


}
