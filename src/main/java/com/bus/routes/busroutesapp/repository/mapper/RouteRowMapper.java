package com.bus.routes.busroutesapp.repository.mapper;

import com.bus.routes.busroutesapp.model.Route;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteRowMapper
      implements RowMapper<Route> {
    @Override
    public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Route route = new Route();
        
        route.setRouteId(rs.getLong("route_id"));
        route.setRouteNumber(rs.getLong("route_number"));
        route.setDescription(rs.getString("description"));
        route.setRouteStartPoint(rs.getString("route_start_point"));
        route.setRouteEndPoint(rs.getString("route_end_point"));
        
        return route;
    }
}
