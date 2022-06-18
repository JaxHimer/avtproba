package com.bus.routes.busroutesapp.repository.impl;

import com.bus.routes.busroutesapp.model.BusRoute;
import com.bus.routes.busroutesapp.model.Route;
import com.bus.routes.busroutesapp.repository.mapper.BusRouteRowMapper;
import com.bus.routes.busroutesapp.repository.mapper.RouteRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusRoutesRepositoryImpl
      extends JDBCRepositoryImpl<BusRoute> {
    @Override
    public BusRoute create(BusRoute newData) {
        String query = """
              insert into bus_routes(bus_id, route_id, start_date, end_date, duration, free_places, price)
              values (?, ?, ?, ?, ?, ?, ?)""";
        Integer id = jdbcTemplate.update(query, newData.getBusId(), newData.getRouteId(),
                                         newData.getStartDate(), newData.getEndDate(),
                                         newData.getDuration(),
                                         newData.getFreePlaces(),
                                         newData.getPrice());
        newData.setId(id.longValue());
        return newData;
    }
    
    @Override
    public BusRoute update(BusRoute updateData) {
        String query = """
              update bus_routes set bus_id = ?, route_id = ?, start_date = ?,  end_date = ?,
              duration = ?, free_places = ?, price = ?
              where id = ?
              """;
        jdbcTemplate.update(query, updateData.getBusId(), updateData.getRouteId(),
                            updateData.getStartDate(), updateData.getEndDate(), updateData.getDuration(),
                            updateData.getFreePlaces(), updateData.getPrice(), updateData.getId());
        return updateData;
    }
    
    @Override
    public void delete(BusRoute deleteData) {
        String query = """
              delete from bus_routes where id = ?""";
        jdbcTemplate.update(query, deleteData.getId());
    }
    
    @Override
    public BusRoute getById(Integer id) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject("select * from bus_routes where id = :id", namedParameters, new BusRouteRowMapper());
    }
    
    @Override
    public List<BusRoute> getAll() {
        return jdbcTemplate.query("SELECT * FROM bus_routes", new BusRouteRowMapper());
    }
}
