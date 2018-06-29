package com.adesk.service.implementation;

import com.adesk.DTO.response.PhoneDTO;
import com.adesk.dao.PhoneDao;
import com.adesk.dao.UserDao;
import com.adesk.models.Phone;
import com.adesk.models.User;
import com.adesk.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<PhoneDTO> findByUserName(String username) {

        List<PhoneDTO> list = new ArrayList<>();

        for (Phone phone : phoneDao.findByUser(userDao.findByUsername(username))) {
            list.add(new PhoneDTO(phone.getId(),phone.getNumber()));
        }
        return list;
    }
}
