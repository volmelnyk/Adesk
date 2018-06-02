package com.adesk.dao;

import com.adesk.models.Phone;
import com.adesk.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneDao extends JpaRepository<Phone,Integer> {

    List<Phone> findByUser(User user);
}
