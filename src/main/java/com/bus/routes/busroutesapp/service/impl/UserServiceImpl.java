package com.bus.routes.busroutesapp.service.impl;

import com.bus.routes.busroutesapp.model.User;

import com.bus.routes.busroutesapp.service.MyService;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl
      implements MyService<User>, UserDetailsService {
}
