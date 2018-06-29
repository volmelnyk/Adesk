package com.adesk.dao;

import com.adesk.models.Advert;
import com.adesk.models.SubCategory;
import com.adesk.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface AdvertDao extends JpaRepository<Advert,Integer> {
    List<Advert> findAllByUser(User user);

    @Modifying
    @Transactional
    @Query("delete from Advert a where a.id = ?1")
    void deleteById(int id);

    void findBySubCategory(SubCategory subCategory);
}
