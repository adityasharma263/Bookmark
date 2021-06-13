package com.hop.bookmark.controller;


import com.hop.bookmark.dto.UserData;
import com.hop.bookmark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public String getToken(@RequestBody(required = true) final UserData userData){
        return userService.getTokenByUserNameAndPass(userData);
    }
}
