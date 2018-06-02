package com.adesk.service.implementation;

import com.adesk.dao.PhoneDao;
import com.adesk.dao.UserDao;
import com.adesk.models.Phone;
import com.adesk.models.User;
import com.adesk.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImpl  implements PhoneService{

    @Autowired
    PhoneDao phoneDao;
    @Autowired
    UserDao userDao;

    @Override
    public void save(Phone phone) {
        phoneDao.save(phone);
    }

    @Override
    public void delete(Phone id) {
        phoneDao.delete(id);
    }

    @Override
    public Phone findById(int id) {
        return phoneDao.getOne(id);
    }

    @Override
    public List<Phone> findByUserName(String username) {

        return phoneDao.findByUser(userDao.findByUsername(username));
    }
}
