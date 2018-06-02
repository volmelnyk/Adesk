package com.adesk.service.implementation;

import com.adesk.DTO.response.PasswordDTO;
import com.adesk.dao.UserDao;
import com.adesk.models.User;
import com.adesk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(int id) {
        userDao.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByUserId(Long id) {
        int id1 = Integer.parseInt(id.toString());
        return userDao.getOne(id1); /*userDao.getOne(Integer.parseInt(id.toString()));*/
    }

    @Override
    public User findByUserEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void update(User user) {
        userDao.save(user);

    }

    @Override
    public void newPhotoSave(File file) {

    }

    @Override
    public void deleteOldPhoto(File file) {

    }

    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.findByUsername(s);
    }
}
