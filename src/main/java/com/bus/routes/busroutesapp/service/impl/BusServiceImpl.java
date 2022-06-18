package com.bus.routes.busroutesapp.service.impl;

import com.bus.routes.busroutesapp.model.Bus;
import com.bus.routes.busroutesapp.repository.impl.BusRepositoryImpl;
import com.bus.routes.busroutesapp.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class BusServiceImpl
      implements MyService<Bus> {
    private final BusRepositoryImpl dao;

    @Autowired
    public BusServiceImpl(BusRepositoryImpl dao) {
        this.dao = dao;
    }

    public Bus create(Bus bus) {
        return dao.create(bus);
    }

    @Override
    public Bus update(Bus data) {
        return dao.update(data);
    }

    @Override
    public void delete(Bus data) {
        dao.delete(data);
    }

    @Override
    public Bus getOne(Integer id) {
        return dao.getById(id);
    }

    @Override
    public List<Bus> getAll() {
        return dao.getAll();
    }
}
