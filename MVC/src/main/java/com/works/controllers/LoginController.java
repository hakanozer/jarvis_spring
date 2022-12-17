package com.works.controllers;

import com.works.entities.Admin;
import com.works.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);
    final AdminService adminService;

    @GetMapping("/")
    public String login() {
        /*
        Admin admin = new Admin();
        admin.setPassword("12345");
        admin.setEmail("veli@mail.com");
        admin.setName("Veli Bilirim");
        adminService.save(admin);*/
        return "login";
    }


    @PostMapping("/login")
    public String adminLogin(@Valid Admin admin, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
        }else {
            boolean status = adminService.login(admin);
            if (status) {
                return "redirect:/dashboard";
            }
        }
        return "login";
    }

}
