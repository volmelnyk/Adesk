package com.adesk.controller;

import com.adesk.DTO.response.ResponseDTO;
import com.adesk.models.Response;
import com.adesk.models.User;
import com.adesk.service.ResponseService;
import com.adesk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
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
    public void addResponse(@RequestBody ResponseDTO responseDTO, Principal principal) {
        User user = (User)userService.loadUserByUsername(principal.getName());
        Response response = new Response();
        response.setUser(user);
        response.setDate(new Date());
        response.setReceiverId(responseDTO.getReceiverId());
        response.setText(responseDTO.getText());
       responseService.save(response);
    }


    @GetMapping("/response/all/{id}")
    public List<ResponseDTO> getAllByReciverId(@PathVariable("id") int id) {
        List<ResponseDTO> responseDTO = new ArrayList<>();

        List<Response> responses = responseService.findAllByReciverId(id);

        for (Response respons : responses) {
            responseDTO.add(new ResponseDTO(respons.getId(),respons.getText(),respons.getReceiverId(),respons.getDate(),respons.getUser().getUsername(),respons.getUser().getPhoto()));
        }

        return responseDTO;
    }

    @GetMapping("/response/delete/{id}")
    public void deleteResponse(@PathVariable("id") int id) {
        responseService.delete(id);
    }


}
