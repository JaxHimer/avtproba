package com.bus.routes.busroutesapp.service.impl;

import com.bus.routes.busroutesapp.dto.UserRoutesDTO;
import com.bus.routes.busroutesapp.model.BusRoute;
import com.bus.routes.busroutesapp.model.User;
import com.bus.routes.busroutesapp.model.UserRoute;
import com.bus.routes.busroutesapp.repository.impl.BusRoutesRepositoryImpl;
import com.bus.routes.busroutesapp.repository.impl.UserRepositoryImpl;
import com.bus.routes.busroutesapp.repository.impl.UserRoutesRepositoryImpl;
import com.bus.routes.busroutesapp.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;



@Slf4j
@Service
public class UserRoutesServiceImpl
      implements MyService<UserRoute> {
    private final UserRoutesRepositoryImpl dao;
    private final UserRepositoryImpl userRepository;

    private final BusRoutesRepositoryImpl busRoutesRepository;
    private final JavaMailSender emailSender;

    @Autowired
    public UserRoutesServiceImpl(UserRoutesRepositoryImpl dao,
                                 JavaMailSender mailSender,
                                 UserRepositoryImpl userRepository,
                                 BusRoutesRepositoryImpl busRoutesRepository) {
        this.dao = dao;
        this.emailSender = mailSender;
        this.userRepository = userRepository;
        this.busRoutesRepository = busRoutesRepository;
    }

    public void sendBookingNumber(final UserRoute userRoute) {
        User user = userRepository.getById(userRoute.getUserId().intValue());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Ваша бронь на билеты");
        message.setText("Номер брони: " + userRoute.getBookingNumber() + ". \nСчастливого путешествия.");
        emailSender.send(message);
    }

    public void bookRoute(UserRoutesDTO userRoutesDTO) {
        UserRoute userRoute = new UserRoute();
        userRoute.setUserId(userRoutesDTO.getUserId());
        userRoute.setRouteId(userRoutesDTO.getRoute().getRouteId());
        userRoute.setStatus(userRoutesDTO.getStatus());
        userRoute.setAmount(userRoutesDTO.getAmount());

        create(userRoute);
        log.info("ROUTE: " + userRoutesDTO.getRoute());
        BusRoute busRoute = busRoutesRepository.getById(userRoutesDTO.getRoute().getBusRouteId().intValue());
        busRoute.setFreePlaces(busRoute.getFreePlaces() - userRoutesDTO.getAmount());
        busRoutesRepository.update(busRoute);
    }

    @Override
    public UserRoute create(UserRoute data) {
        data.setBookingNumber(RandomStringUtils.randomAlphanumeric(20));
        dao.create(data);
        sendBookingNumber(data);
        return data;
    }

    @Override
    public UserRoute update(UserRoute data) {
        return dao.update(data);
    }

    @Override
    public void delete(UserRoute data) {
        dao.delete(data);
    }

    @Override
    public UserRoute getOne(Integer id) {
        return dao.getById(id);
    }

    @Override
    public List<UserRoute> getAll() {
        return dao.getAll();
    }

    public List<UserRoutesDTO> getUserRouts(Integer userId) {
        return dao.getUserRoutes(userId);
    }
}
