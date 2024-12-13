package com.gupaoedu.controller;

import com.gupaoedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/user/query")
    public String query(){
        return userService.query().toString();
    }
}
