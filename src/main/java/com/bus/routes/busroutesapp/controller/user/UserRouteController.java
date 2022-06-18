package com.bus.routes.busroutesapp.controller.user;

import com.bus.routes.busroutesapp.service.impl.RouteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/user/route")
public class UserRouteController {
    
    private final RouteServiceImpl service;
    
    public UserRouteController(RouteServiceImpl service) {
        this.service = service;
    }
    
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("routes", service.getAll());
        return "users/route/index";
    }
    
    @GetMapping("/{routeId}")
    public String index(@PathVariable Integer routeId, Model model) {
        log.info(service.getRouteDTOByRouteId(routeId).toString());
        model.addAttribute("busRoutes", service.getRouteDTOByRouteId(routeId));
        return "users/busRoute/busRoutes";
    }
    
}
