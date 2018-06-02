package com.adesk.controller;

import com.adesk.models.Response;
import com.adesk.models.User;
import com.adesk.service.ResponseService;
import com.adesk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class ResponseController {
    @Autowired
    private ResponseService responseService;
    @Autowired
    private UserService userService;

    @PostMapping("/response/add")
    public void addResponse(@RequestBody Response response, Principal principal) {
        User user = (User)userService.loadUserByUsername(principal.getName());
        response.setUser(user);
        response.setDate(new Date());
        response.setReceiverId(response.getReceiverId());
        response.setSenderName(user.getUsername());
        response.setSenderPhoto(user.getPhoto());
        responseService.save(response);
    }

    @GetMapping("/response/all")
    public List<Response> getAllREsponse() {
        return responseService.findAll();
    }

    @GetMapping("/response/all/{id}")
    public List<Response> getAllByReciverId(@PathVariable("id") int id) {
        return responseService.findAllByReciverId(id);
    }

    @GetMapping("/response/delete/{id}")
    public void deleteResponse(@PathVariable("id") int id) {
        responseService.delete(id);
    }


}
