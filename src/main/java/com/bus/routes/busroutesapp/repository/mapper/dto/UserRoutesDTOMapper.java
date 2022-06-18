package com.bus.routes.busroutesapp.repository.mapper.dto;

import com.bus.routes.busroutesapp.dto.RouteDTO;
import com.bus.routes.busroutesapp.dto.UserRoutesDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoutesDTOMapper
      implements RowMapper<UserRoutesDTO> {
    @Override
    public UserRoutesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserRoutesDTO userRoutesDTO = new UserRoutesDTO();
        RouteDTO routeDTO = new RouteDTO();
        
        userRoutesDTO.setUserRouteId(rs.getLong("ur_id"));
        userRoutesDTO.setUserId(rs.getLong("user_id"));
        userRoutesDTO.setBookingNum(rs.getString("booking_number"));
        userRoutesDTO.setAmount(rs.getLong("amount"));
        userRoutesDTO.setStatus(rs.getString("status"));
        
        routeDTO.setBusRouteId(rs.getLong("bus_route_id"));
        routeDTO.setBusId(rs.getLong("bus_id"));
        routeDTO.setRouteId(rs.getLong("route_id"));
        routeDTO.setRouteStart(rs.getString("route_start_point"));
        routeDTO.setRouteEnd(rs.getString("route_end_point"));
        routeDTO.setDescription(rs.getString("description"));
        routeDTO.setStartDate(rs.getDate("start_date"));
        routeDTO.setEndDate(rs.getDate("end_date"));
        routeDTO.setDuration(rs.getString("duration"));
        routeDTO.setFreePlaceAmount(rs.getLong("free_places"));
        routeDTO.setPrice(rs.getString("price"));
        routeDTO.setBusNumber(rs.getString("bus_number"));
        routeDTO.setRouteNum(rs.getLong("route_number"));
        
        userRoutesDTO.setRoute(routeDTO);
        
        return userRoutesDTO;
    }
}
