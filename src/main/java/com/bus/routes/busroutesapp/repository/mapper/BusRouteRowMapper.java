package com.bus.routes.busroutesapp.repository.mapper;

import com.bus.routes.busroutesapp.model.BusRoute;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusRouteRowMapper
      implements RowMapper<BusRoute> {
    @Override
    public BusRoute mapRow(ResultSet rs, int rowNum) throws SQLException {
        final BusRoute busRoute = new BusRoute();
        
        busRoute.setId(rs.getLong("id"));
        busRoute.setBusId(rs.getLong("bus_id"));
        busRoute.setRouteId(rs.getLong("route_id"));
        busRoute.setStartDate(rs.getDate("start_date"));
        busRoute.setEndDate(rs.getDate("end_date"));
        busRoute.setDuration(rs.getString("duration"));
        busRoute.setFreePlaces(rs.getLong("free_places"));
        busRoute.setPrice(rs.getString("price"));
        
        return busRoute;
    }
}
