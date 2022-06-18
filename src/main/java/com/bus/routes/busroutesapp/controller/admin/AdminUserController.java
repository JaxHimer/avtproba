package com.bus.routes.busroutesapp.controller.admin;

import com.bus.routes.busroutesapp.model.User;
import com.bus.routes.busroutesapp.service.MyService;
import com.bus.routes.busroutesapp.service.impl.UserRoutesServiceImpl;
import com.bus.routes.busroutesapp.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//@Slf4j - логи (log.info())
//@Controller - пометка для спринга что этот класс является контроллером
//@RequestMapping("/admin/user") - мапинг по которому будет доступен этот контроллер (например этот доступен по такому url localhost/admin/user)
@Slf4j
@Controller
@RequestMapping("admin/user")
public class AdminUserController {

    private final UserServiceImpl service;
    private final UserRoutesServiceImpl userRoutesService;

    public AdminUserController(UserServiceImpl service, UserRoutesServiceImpl userRoutesService) {
        this.service = service;
        this.userRoutesService = userRoutesService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("users", service.getAllByRoleID(1));
        return "admin/user/index";
    }

    //    @PathVariable переменная взятая из ссылки (к примеру тут если будет запрос admin/user/1, в переменную userId будет записано значение 1)
    @GetMapping("{userId}")
    public String viewUserBook(Model model, @PathVariable Integer userId) {
        log.info(userRoutesService.getUserRouts(userId).toString());
        model.addAttribute("userRoutes", userRoutesService.getUserRouts(userId));
        return "admin/user/userBookings";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserRoute(@PathVariable Integer id) {
        userRoutesService.delete(userRoutesService.getOne(id));
        return "redirect:/admin/user";
    }
}
