package com.bus.routes.busroutesapp.controller.conductor;

import com.bus.routes.busroutesapp.model.Route;
import com.bus.routes.busroutesapp.service.impl.BusServiceImpl;
import com.bus.routes.busroutesapp.service.impl.RouteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Slf4j
@Controller
@RequestMapping("/conductor/route")
public class RouteController {

    private final RouteServiceImpl service;
    private final BusServiceImpl busService;

    public RouteController(RouteServiceImpl service, BusServiceImpl busService) {
        this.service = service;
        this.busService = busService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("routes", service.getAll());
        model.addAttribute("buses", busService.getAll());
        return "conductor/route/index";
    }

    @GetMapping("/add")
    public String addBus() {
        return "conductor/route/addRoute";
    }

    @GetMapping("/{id}")
    public String index(@PathVariable Integer id, Model model) {
        model.addAttribute("busRoutes", service.getRouteDTOByRouteId(id));
        return "conductor/busRoute/index";
    }

    @PostMapping("/add")
    public String addRoute(@ModelAttribute("routeForm") @Valid Route route,
                           BindingResult bindingResult,
                           Model model) {
        log.info(route.toString());
        service.create(route);
        return "redirect:/conductor/route";
    }

    @GetMapping("/delete/{id}")
    public String deleteBus(@PathVariable Integer id) {
        service.delete(service.getOne(id));
        return "redirect:/conductor/route";
    }
}
