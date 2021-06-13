package com.hop.bookmark.controller;


import com.hop.bookmark.dto.UserData;
import com.hop.bookmark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    @ResponseBody
    public String addUser(@RequestBody(required = true) final UserData userData){
        return userService.addUser(userData);

    }

}
