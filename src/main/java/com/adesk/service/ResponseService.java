package com.adesk.service;


import com.adesk.models.Response;
import com.adesk.models.User;

import java.util.List;

public interface ResponseService {

    void save(Response user);

    void delete(int id);

    List<Response> findAll();
    List<Response> findAllByReciverId(int id);
}
