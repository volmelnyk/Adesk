package com.adesk.dao;

import com.adesk.models.Response;
import com.adesk.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseDao extends JpaRepository<Response,Integer> {

    List<Response> findByUser(User user);
    List<Response> findAllByReceiverId(int id);
}
