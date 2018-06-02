package com.adesk.service;

import com.adesk.models.*;

import java.util.List;

public interface PhoneService {

    void save(Phone phone);
    void delete(Phone id);
    Phone findById(int id);
    List<Phone> findByUserName(String username);
}
