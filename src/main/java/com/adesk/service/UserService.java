package com.adesk.service;

import com.adesk.DTO.response.PasswordDTO;
import com.adesk.DTO.response.UserDTO;
import com.adesk.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.File;
import java.util.List;

public interface UserService extends UserDetailsService {

    void save(User user);

    void delete(int id);

    List<UserDTO> findAll();

    User findByUserId(Long id);

    User findByUserEmail(String email);

    void update(User user);

    void newPhotoSave(File file);

    void deleteOldPhoto(File file);

    void blockUnblock(String id);

}
