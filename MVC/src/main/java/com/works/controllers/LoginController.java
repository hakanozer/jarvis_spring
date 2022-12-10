package com.works.controllers;

import com.works.entities.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String adminLogin(Admin admin) {
        logger.info( admin.toString() );
        return "login";
    }

}
