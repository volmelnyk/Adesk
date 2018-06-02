package com.adesk.dao;

import com.adesk.models.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertDao extends JpaRepository<Advert,Integer> {
}
