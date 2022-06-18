package com.bus.routes.busroutesapp.service.impl;

import com.bus.routes.busroutesapp.model.BusRoute;
import com.bus.routes.busroutesapp.repository.impl.BusRepositoryImpl;
import com.bus.routes.busroutesapp.repository.impl.BusRoutesRepositoryImpl;
import com.bus.routes.busroutesapp.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class BusRouteServiceImpl
      implements MyService<BusRoute> {

    //Объект репозитория который используется для данного сервиса
    private final BusRoutesRepositoryImpl dao;

    @Autowired
    public BusRouteServiceImpl(BusRoutesRepositoryImpl dao) {
        this.dao = dao;
    }

    //Вызов из репозитория метода на создание автобуса
    @Override
    public BusRoute create(BusRoute data) {
        return dao.create(data);
    }

    @Override
    public BusRoute update(BusRoute data) {
        return dao.update(data);
    }

    @Override
    public void delete(BusRoute data) {
        dao.delete(data);
    }

    @Override
    public BusRoute getOne(Integer id) {
        return dao.getById(id);
    }

    @Override
    public List<BusRoute> getAll() {
        return dao.getAll();
    }
}
