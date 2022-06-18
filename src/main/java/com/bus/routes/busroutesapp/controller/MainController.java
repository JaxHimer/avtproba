package com.bus.routes.busroutesapp.controller;

import com.bus.routes.busroutesapp.model.User;
import com.bus.routes.busroutesapp.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@Slf4j
public class MainController {

    private final UserServiceImpl service;

    public MainController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("test", "test");
        return "index";
    }

    @GetMapping("/registration")
    public String registration() {
        return "users/registration";
    }

    @GetMapping("/acceptEmail/{userId}")
    public String acceptEmail(@PathVariable Integer userId) {
        service.verifyEmail(userId);
        return "users/registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User user) {
        log.info(user.toString());
        user.setRoleId(1L);
        user.setEmailSubmitted(false);
        service.create(user);
        return "redirect:login";
    }
}
