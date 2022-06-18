package com.bus.routes.busroutesapp.controller.user;

import com.bus.routes.busroutesapp.service.impl.UserRoutesServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/user/user-route")
public class UserUserRouteController {
    
    private final UserRoutesServiceImpl service;
    
    public UserUserRouteController(UserRoutesServiceImpl service) {
        this.service = service;
    }
    
    @GetMapping("{userId}")
    public String index(Model model, @PathVariable Integer userId) {
        log.info(service.getUserRouts(userId).toString());
        model.addAttribute("userRoutes", service.getUserRouts(userId));
        return "users/userRoute/index";
    }
}
