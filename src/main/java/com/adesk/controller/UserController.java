package com.adesk.controller;

import com.adesk.DTO.response.PasswordDTO;
import com.adesk.DTO.response.UserDTO;
import com.adesk.models.Phone;
import com.adesk.models.Role;
import com.adesk.models.User;
import com.adesk.service.CityService;
import com.adesk.service.ImageLoadService;
import com.adesk.service.PhoneService;
import com.adesk.service.UserService;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.*;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    CityService cityService;
    @Autowired
    private UserService userService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    ImageLoadService imageLoadService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<UserDTO> listUser(Authentication authentication) {
        return userService.findAll();
    }

//    @RequestMapping(value = "/phone", method = RequestMethod.GET)
//    public List<Phone> listPhones(Authentication authentication) {
//        return phoneService.findByUserName("root1");
//    }


    @PostMapping("/update")
    public void userUpdate(@RequestBody UserDTO userToUpdate, Principal principal) {
        try {
            User user = (User) userService.loadUserByUsername(principal.getName());
            user.setCity(userToUpdate.getCity());
            user.setEmail(userToUpdate.getEmail());
            user.setFirstName(userToUpdate.getFirstName());
            user.setSecondName(userToUpdate.getSecondName());
            userService.update(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public UserDTO oneUser(@PathVariable(value = "username") String id) {
        User user = (User) userService.loadUserByUsername(id);

        UserDTO userDTO = new UserDTO(user.getId(),user.getEmail(),user.getFirstName(),user.getSecondName(),user.getUsername(),
                user.getPhoto(),user.getCity(),user.isAccountNonLocked(),user.getPhones());

        return userDTO;
    }

    @RequestMapping(value = "/register-user", method = RequestMethod.POST)
    public void saveUser(@RequestBody User user) {

        user.setPhoto("noavatar.jpg");
        user.setPassword(encoder.encode(user.getPassword()));
        userService.save(user);
    }

    @PostMapping("/register-admin")
    public void saveAdminUser(@RequestBody User user) {
        user.setPhoto("noavatar.jpg");
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_ADMIN);
        userService.save(user);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordDTO password, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());

        String encodePassword = encoder.encode(password.oldPassword);
        if (!encoder.matches(password.oldPassword, user.getPassword()))
            return new ResponseEntity("InvalidPassword", HttpStatus.BAD_REQUEST);
        user.setPassword(encoder.encode(password.newPassword));
        userService.update(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/change-to-defoult_photo")
    public void setDefoultPhoto(Principal principal) throws IOException {
        User user = (User) userService.loadUserByUsername(principal.getName());
        if (!user.getPhoto().equals("noavatar.jpg"))
            Files.deleteIfExists(FileSystems.getDefault().getPath(getPhotoPath(), user.getPhoto()));
        user.setPhoto("noavatar.jpg");

        userService.update(user);
    }

    private String getPhotoPath() {
        return System.getProperty("user.home") + File.separator + "Documents" + File.separator
                + "IdeaProjects" + File.separator + "Course Project" + File.separator + "Project" +
                File.separator + "adesk" + File.separator + "client" + File.separator + "src" +
                File.separator + "assets" + File.separator + "avatar" + File.separator;
    }

    @PostMapping("/upload-avatar")
    public void uploadFile(@RequestParam("file") MultipartFile upFile, Principal principal) {
        try {
            String path = getPhotoPath();
            imageLoadService.load(upFile, path);

            User user = (User) userService.loadUserByUsername(principal.getName());

            if (!user.getPhoto().equals("noavatar.jpg")) {
                Files.deleteIfExists(FileSystems.getDefault().getPath(path, user.getPhoto()));
            }
            user.setPhoto(upFile.getOriginalFilename());
            userService.update(user);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/block/{username}")
    public void blockUnBlock(@PathVariable(value = "username") String username)
    {
        System.out.println(username);
        userService.blockUnblock(username);
    }

}
