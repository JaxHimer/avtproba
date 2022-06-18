package com.bus.routes.busroutesapp.controller.user;

import com.bus.routes.busroutesapp.dto.UserRoutesDTO;
import com.bus.routes.busroutesapp.service.impl.BusRouteServiceImpl;
import com.bus.routes.busroutesapp.service.impl.RouteServiceImpl;
import com.bus.routes.busroutesapp.service.impl.UserRoutesServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/user/bus-route")
public class UserBusRouteController {
    
    private final UserRoutesServiceImpl service;
    private final RouteServiceImpl routeService;
    private final BusRouteServiceImpl busRouteService;
    private final UserRoutesServiceImpl userRoutesService;
    
    public UserBusRouteController(UserRoutesServiceImpl service,
                                  RouteServiceImpl routeService,
                                  BusRouteServiceImpl busRouteService, UserRoutesServiceImpl userRoutesService) {
        this.service = service;
        this.routeService = routeService;
        this.busRouteService = busRouteService;
        this.userRoutesService = userRoutesService;
    }
    
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("userRoutes", service.getAll());
        log.info(service.getAll().toString());
        return "users/busRoute/index";
    }
    
    @GetMapping("/book/{routeId}/{busRouteId}")
    public String book(@PathVariable Integer routeId, Model model, @PathVariable Integer busRouteId) {
        model.addAttribute("routeId", routeId);
        model.addAttribute("busRouteId", busRouteId);
        return "users/busRoute/bookBusRoute";
    }
    
    @GetMapping("/{userId}")
    public String busRoutes(@PathVariable Integer userId, Model model) {
        log.info(userRoutesService.getUserRouts(userId).toString());
        model.addAttribute("userRoutes", userRoutesService.getUserRouts(userId));
        return "users/busRoute/busRoutes";
    }
    
    @PostMapping("/book/{routeId}/{busRouteId}/{userId}")
    public String book(@ModelAttribute("bookBusForm") @Valid UserRoutesDTO userRoutesDTO,
                       @PathVariable Integer routeId,
                       @PathVariable Integer busRouteId,
                       @PathVariable Long userId
                      ) {
        userRoutesDTO.setRoute(routeService.getRouteDTOByRouteIdAndBusRouteId(busRouteId, routeId));
        
        log.info(userRoutesDTO.toString());
        userRoutesDTO.setUserId(userId);
        userRoutesDTO.setStatus("СОЗДАН");
        service.bookRoute(userRoutesDTO);
        return "redirect:/user/route";
    }
    
}
