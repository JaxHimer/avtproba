package com.bus.routes.busroutesapp.service.impl;

import com.bus.routes.busroutesapp.model.User;
import com.bus.routes.busroutesapp.repository.impl.UserRepositoryImpl;
import com.bus.routes.busroutesapp.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class UserServiceImpl
      implements MyService<User>, UserDetailsService {

    @Value("${spring.security.user.name}")
    private String adminUserName;
    @Value("${spring.security.user.password}")
    private String adminPassword;
    @Value("${spring.security.user.roles}")
    private String adminRole;

    private final JavaMailSender emailSender;
    private final UserRepositoryImpl userRepository;

    @Autowired
    public UserServiceImpl(UserRepositoryImpl repository,
                           JavaMailSender mailSender) {
        this.userRepository = repository;
        this.emailSender = mailSender;
    }

    public void sendVerificationEmail(String email, String verificationLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        //message.setFrom("noreplybusservice@busroutes.com");
        message.setTo(email);
        message.setSubject("Верификация email на сервисе MyBusRoutes");
        message.setText("Добрый день. Вы получили это письмо, так как зарегистрировались на сайте busroutes.com." +
                "Для возможности бронирования билетов на автобусы, верифицируйте ваш почтовый адрес перейдя по ссылке: " + verificationLink);
        emailSender.send(message);
    }

    @Override
    public User create(User data) {
        userRepository.create(data);
        sendVerificationEmail(data.getEmail(), "http://localhost:8090/acceptEmail/" + data.getUserId());
        return data;
    }

    public void verifyEmail(Integer id) {
        User user = userRepository.getById(id);
        user.setEmailSubmitted(true);
        userRepository.update(user);
    }

    @Override
    public User update(User data) {
        return userRepository.update(data);
    }

    @Override
    public void delete(User data) {
        userRepository.delete(data);
    }

    @Override
    public User getOne(Integer id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    public List<User> getAllByRoleID(Integer roleId) {
        return userRepository.getByRoleId(roleId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(adminUserName)) {
            return org.springframework.security.core.userdetails.User
                    .builder()
                    .username(adminUserName)
                    .password(adminPassword)
                    .roles(adminRole)
                    .build();
        }
        else {
            User user = userRepository.findByUsername(username);
            List<GrantedAuthority> authorities
                    = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRoleId() == 1L ? "USER" : "CONDUCTOR"));
            System.out.println("authorities: " + authorities);
            return new CustomUserDetails(user.getUserId().intValue(), username, user.getPassword(),
                    authorities);
        }
    }
}
