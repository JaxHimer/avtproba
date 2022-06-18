package com.bus.routes.busroutesapp.controller.conductor;

import com.bus.routes.busroutesapp.model.BusRoute;
import com.bus.routes.busroutesapp.service.MyService;
import com.bus.routes.busroutesapp.service.impl.BusServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/conductor/bus-route")
public class BusRouteController {

    private final MyService<BusRoute> service;
    private final BusServiceImpl busService;

    public BusRouteController(MyService<BusRoute> service, BusServiceImpl busService) {
        this.service = service;
        this.busService = busService;
    }

    @GetMapping("/add/{routeId}")
    public String index(Model model, @PathVariable Integer routeId) {
        model.addAttribute("buses", busService.getAll());
        model.addAttribute("routeId", routeId);
        return "conductor/route/addBusToRoute";

    }

    @PostMapping("/add")
    public String addBusRoute(@ModelAttribute("busRouteForm") @Valid BusRoute busRoute) {
        log.info(busRoute.toString());
        service.create(busRoute);
        return "redirect:/conductor/route";
    }

    @GetMapping("/delete/{id}")
    public String deleteBusRoute(@PathVariable Integer id) {
        service.delete(service.getOne(id));
        return "redirect:/conductor/route";
    }
}
