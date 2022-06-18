package com.bus.routes.busroutesapp.controller.conductor;

import com.bus.routes.busroutesapp.model.Bus;
import com.bus.routes.busroutesapp.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;



@Controller
@Slf4j
@RequestMapping("/conductor/bus")
public class BusController {

    private final MyService<Bus> service;

    public BusController(MyService<Bus> service) {
        this.service = service;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("buses", service.getAll());
        return "conductor/bus/index";
    }

    @GetMapping("/add")
    public String addBus() {
        return "/conductor/bus/addBus";
    }

    @PostMapping("/add")
    public String addBus(@ModelAttribute("busForm") @Valid Bus bus) {
        log.info(bus.toString());
        service.create(bus);
        return "redirect:/conductor/bus";
    }

    @GetMapping("/delete/{id}")
    public String deleteBus(@PathVariable Integer id) {
        service.delete(service.getOne(id));
        return "redirect:/conductor/bus";
    }
}
