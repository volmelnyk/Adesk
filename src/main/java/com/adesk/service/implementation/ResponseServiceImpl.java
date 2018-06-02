package com.adesk.service.implementation;

import com.adesk.dao.ResponseDao;
import com.adesk.models.Response;
import com.adesk.models.User;
import com.adesk.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseServiceImpl implements ResponseService {

    @Autowired
    ResponseDao responseDao;

    @Override
    public void save(Response user) {
        responseDao.save(user);
    }

    @Override
    public void delete(int id) {
        responseDao.deleteById(id);
    }

    @Override
    public List<Response> findAll() {
        return responseDao.findAll();
    }

    @Override
    public List<Response> findAllByReciverId(int id) {
        return responseDao.findAllByReceiverId(id);
    }


}
