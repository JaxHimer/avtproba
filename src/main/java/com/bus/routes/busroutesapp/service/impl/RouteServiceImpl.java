package com.bus.routes.busroutesapp.service.impl;

import com.bus.routes.busroutesapp.dto.RouteDTO;
import com.bus.routes.busroutesapp.model.Route;
import com.bus.routes.busroutesapp.repository.impl.RoutesRepositoryImpl;
import com.bus.routes.busroutesapp.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl
      implements MyService<Route> {

    private final RoutesRepositoryImpl dao;

    @Autowired
    public RouteServiceImpl(RoutesRepositoryImpl dao) {
        this.dao = dao;
    }

    @Override
    public Route create(Route data) {
        return dao.create(data);
    }

    @Override
    public Route update(Route data) {
        return dao.update(data);
    }

    @Override
    public void delete(Route data) {
        dao.delete(data);
    }

    @Override
    public Route getOne(Integer id) {
        return dao.getById(id);
    }

    @Override
    public List<Route> getAll() {
        return dao.getAll();
    }

    public RouteDTO getRouteDTOByRouteIdAndBusRouteId(Integer busRouteId, Integer routeId){
        return dao.getRouteDTOByRouteIdAndBusRouteId(busRouteId, routeId);
    }
    public List<RouteDTO> getRouteDTO(){
        return dao.getRouteDTOList();
    }

    public List<RouteDTO> getRouteDTOByRouteId(Integer routeId) {
        return dao.getRouteDTOListByRouteId(routeId);
    }
}
