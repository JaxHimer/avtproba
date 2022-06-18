package com.bus.routes.busroutesapp.repository.impl;

import com.bus.routes.busroutesapp.dto.RouteDTO;
import com.bus.routes.busroutesapp.model.Route;
import com.bus.routes.busroutesapp.repository.mapper.RouteRowMapper;
import com.bus.routes.busroutesapp.repository.mapper.dto.RouteDTOMapper;
import com.bus.routes.busroutesapp.repository.mapper.dto.UserRoutesDTOMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoutesRepositoryImpl
      extends JDBCRepositoryImpl<Route> {
    @Override
    public Route create(Route newData) {
        String query = """
              insert into routes(route_number, description, route_start_point, route_end_point)
              values (?, ?, ?, ?)""";
        Integer id = jdbcTemplate.update(query, newData.getRouteNumber(), newData.getDescription(),
                                         newData.getRouteStartPoint(), newData.getRouteEndPoint());
        newData.setRouteId(id.longValue());
        return newData;
    }
    
    @Override
    public Route update(Route updateData) {
        String query = """
              update routes set route_number = ?, description = ?, route_start_point = ?,  route_end_point = ?
              where route_id = ?
              """;
        jdbcTemplate.update(query, updateData.getRouteNumber(), updateData.getDescription(),
                            updateData.getRouteStartPoint(), updateData.getRouteEndPoint(), updateData.getRouteId());
        return updateData;
    }
    
    @Override
    public void delete(Route deleteData) {
        String query = """
              delete from routes where route_id = ?""";
        jdbcTemplate.update(query, deleteData.getRouteId());
    }
    
    @Override
    public Route getById(Integer id) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("route_id", id);
        return namedParameterJdbcTemplate.queryForObject("select * from routes where route_id = :route_id", namedParameters, new RouteRowMapper());
    }
    
    @Override
    public List<Route> getAll() {
        return jdbcTemplate.query("SELECT * FROM routes", new RouteRowMapper());
    }
    
    public RouteDTO getRouteDTOByRouteIdAndBusRouteId(Integer busRouteId, Integer routeId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
              .addValue("route_id", routeId)
              .addValue("bus_route_id", busRouteId);
        return namedParameterJdbcTemplate.queryForObject("""
                                                               select br.id as bus_route_id, b.bus_id as bus_id, r.route_id as route_id, r.route_start_point,
                                                                      r.route_end_point, r.description, br.start_date, br.end_date, br.duration, br.free_places,
                                                                      br.price, b.bus_number, r.route_number
                                                               from routes r,
                                                                    bus_routes br,
                                                                    busses b
                                                               where r.route_id = :route_id and br.id = :bus_route_id
                                                               and br.bus_id = b.bus_id
                                                               and br.route_id = r.route_id""", namedParameters, new RouteDTOMapper());
    }
    
    public List<RouteDTO> getRouteDTOList() {
        return jdbcTemplate.query("""
                                        select br.id as bus_route_id, b.bus_id as bus_id, r.route_id as route_id, r.route_start_point,
                                               r.route_end_point, r.description, br.start_date, br.end_date, br.duration, br.free_places,
                                               br.price, b.bus_number, r.route_number
                                        from routes r,
                                             bus_routes br,
                                             busses b
                                        where br.bus_id = b.bus_id
                                        and br.route_id = r.route_id""", new RouteDTOMapper());
    }
    
    public List<RouteDTO> getRouteDTOListByRouteId(Integer routeId) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("route_id", routeId);
        return namedParameterJdbcTemplate.query("""
                                                       select
                                                           br.id               as bus_route_id,
                                                           b.bus_id            as bus_id,
                                                           r.route_id          as route_id,
                                                           r.route_start_point,
                                                           r.route_end_point,
                                                           r.description,
                                                           br.start_date,
                                                           br.end_date,
                                                           br.duration,
                                                           br.free_places,
                                                           br.price,
                                                           b.bus_number,
                                                           r.route_number
                                                       from routes r,
                                                            bus_routes br,
                                                            busses b
                                                      where br.bus_id = b.bus_id
                                                          and br.route_id = r.route_id
                                                          and r.route_id = :route_id
                                                                     """, namedParameters, new RouteDTOMapper());
        
    }
}
