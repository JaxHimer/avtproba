package com.bus.routes.busroutesapp.repository.mapper.dto;

import com.bus.routes.busroutesapp.dto.RouteDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteDTOMapper
      implements RowMapper<RouteDTO> {
    @Override
    //Получает resultSet из запроса и парсит его в объект
    public RouteDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        RouteDTO routeDTO = new RouteDTO();
        //указывается название колонки у то в какой тип Java ее надо преобразовать, затем через сеттер устанавливается значение
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
        return routeDTO;
    }
}
