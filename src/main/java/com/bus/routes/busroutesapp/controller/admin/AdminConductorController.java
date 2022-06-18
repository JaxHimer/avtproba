package com.bus.routes.busroutesapp.controller.admin;

import com.bus.routes.busroutesapp.model.User;
import com.bus.routes.busroutesapp.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
//@Slf4j - логи (log.info())
//@Controller - пометка для спринга что этот класс является контроллером
//@RequestMapping("/admin/user") - мапинг по которому будет доступен этот контроллер (например этот доступен по такому url localhost/admin/user)
@Slf4j
@Controller
@RequestMapping("/admin/conductor")
public class AdminConductorController {

    //Инжект сервиса в класс
    private final UserServiceImpl service;


    public AdminConductorController(UserServiceImpl service) {
        this.service = service;
    }

    /***
     *
     * @param model то куда будут положены данные для страницы
     * @return путь до страницы (путь до файла, а не url)
     */
    @GetMapping("") // По какому url будет доступен данный метод (В данном случае admin/conductor)
    public String index(Model model) {
        //Положили в модель в объект с названием указанным в 1 параметре
        //Потом на фронте забираем этот объект и с ним работает
        model.addAttribute("users", service.getAllByRoleID(2));
        //Вернули путь до файла который нужно отобразить после отработки данного метода
        return "admin/conductor/index";
    }

    @GetMapping("/add")
    public String addUser() {
        //Вернули путь до файла который нужно отобразить после отработки данного метода
        return "admin/conductor/addConductor";
    }

    /**
     *
     * @param user - объект который нам пришел с фронта (берется по названию которое было указано на фронте в параметре th:object)
     * @return редирект на указанный url (не путать с просто ретерном, здесь он переводит пользователя на другой юрл)
     */
    @PostMapping("/add") // По какому url будет доступен данный метод (В данном случае admin/conductor/add)
    public String addUser(@ModelAttribute("conductorForm") @Valid User user) {
        log.info(user.toString());
        user.setRoleId(2L);
        user.setEmailSubmitted(true);
        //Вызов метода по созданию пользователя
        service.create(user);
        //редирект на указанный url (не путать с просто ретерном, здесь он переводит пользователя на другой юрл)
        return "redirect:/admin/conductor";
    }
}
