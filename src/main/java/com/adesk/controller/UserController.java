package com.adesk.controller;

import com.adesk.DTO.response.PasswordDTO;
import com.adesk.DTO.response.UserEditDetailsDTO;
import com.adesk.models.*;
import com.adesk.service.CityService;
import com.adesk.service.PhoneService;
import com.adesk.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> listUser(Authentication authentication) {
        return userService.findAll();
    }

    @RequestMapping(value = "/phone", method = RequestMethod.GET)
    public List<Phone> listPhones(Authentication authentication) {
        return phoneService.findByUserName("root1");
    }


    @PostMapping("/update")
    public void userUpdate(@RequestBody User userToUpdate) {
        try {

            userService.update(userToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public User oneUser(@PathVariable(value = "username") String id) {
        User user = (User) userService.loadUserByUsername(id);


        return new User(user.getId(), user.getEmail(), user.getFirstName(), user.getSecondName(), user.getUsername(), user.getPassword(),
                user.getDescription(), user.getPhoto(), user.getRole(),
                user.isAccountNonExpired(), user.isAccountNonLocked(), user.isCredentialsNonExpired(),
                user.isEnabled(), user.getAdverts(), user.getCity(), user.getResponses(), user.getPhones());
    }

    @RequestMapping(value = "/signOut", method = RequestMethod.POST)
    public void saveUser(@RequestBody User user) {

        user.setPhoto("noavatar.jpg");
        user.setPassword(encoder.encode(user.getPassword()));
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

    @PostMapping("/upload-avatar")
    public void uploadFile(@RequestParam("file")MultipartFile upFile)
    {

    }

}
