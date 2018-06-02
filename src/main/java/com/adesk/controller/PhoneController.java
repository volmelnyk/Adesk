package com.adesk.controller;


import com.adesk.models.Phone;
import com.adesk.models.User;
import com.adesk.service.PhoneService;
import com.adesk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public Phone getPhoneById(@PathVariable(value = "id") int id) {

        Phone phone = new Phone();

       Phone tmp = phoneService.findById(id);

        phone.setNumber(tmp.getNumber());
        return phone;
    }

    @PostMapping("/add-phone")
    public void addPhone (@RequestBody Phone phone, Principal principal) {
        Phone newPhone = new Phone();
        newPhone.setNumber(phone.getNumber());

        newPhone.setUser((User)userService.loadUserByUsername(principal.getName()));
        System.out.println(newPhone);
        phoneService.save(newPhone);
    }

    @GetMapping("/get-alll-pnones-by/{username}")
    public List<Phone> getAllPhonesByUsername(@PathVariable(value = "username") String username)
    {
        return phoneService.findByUserName(username);
    }

    @DeleteMapping("/delete-phone/{id}")
    public void delete(@PathVariable(value = "id") int id)
    {
        phoneService.delete(phoneService.findById(id));
    }



}
