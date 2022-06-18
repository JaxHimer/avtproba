package com.bus.routes.busroutesapp.repository.impl;

import com.bus.routes.busroutesapp.dto.UserRoutesDTO;
import com.bus.routes.busroutesapp.model.UserRoute;
import com.bus.routes.busroutesapp.repository.mapper.UserRouteRowMapper;
import com.bus.routes.busroutesapp.repository.mapper.dto.UserRoutesDTOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class UserRoutesRepositoryImpl
      extends JDBCRepositoryImpl<UserRoute> {
    @Override
    public UserRoute create(UserRoute newData) {
        String query = """
              insert into user_routes(user_id, route_id, booking_number, amount, status)
              values (?, ?, ?, ?, ?)""";
        log.info("ROUTE_ID: " + newData.getRouteId());
        Integer id = jdbcTemplate.update(query, newData.getUserId(),
                                         newData.getRouteId(), newData.getBookingNumber(),
                                         newData.getAmount(), newData.getStatus());
        newData.setId(id.longValue());
        return newData;
    }
    
    @Override
    public UserRoute update(UserRoute updateData) {
        String query = """
              update user_routes set user_id = ?, route_id = ?, booking_number = ?, amount = ?, status = ?
              where id = ?
              """;
        jdbcTemplate.update(query, updateData.getUserId(), updateData.getRouteId(), updateData.getBookingNumber(),
                            updateData.getAmount(), updateData.getStatus(), updateData.getId());
        return updateData;
    }
    
    @Override
    public void delete(UserRoute deleteData) {
        String query = """
              delete from user_routes where id = ?""";
        jdbcTemplate.update(query, deleteData.getId());
    }
    
    @Override
    public UserRoute getById(Integer id) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject("select * from user_routes where id = :id", namedParameters, new UserRouteRowMapper());
    }
    
    @Override
    public List<UserRoute> getAll() {
        return jdbcTemplate.query("SELECT * FROM user_routes", new UserRouteRowMapper());
    }
    
    public List<UserRoutesDTO> getUserRoutes(Integer userId) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("user_id", userId);
        return namedParameterJdbcTemplate.query("""
                                                      select ur.id ur_id, ur.user_id, ur.booking_number, ur.amount, ur.status, br.id as bus_route_id, b.bus_id as bus_id, r.route_id as route_id, r.route_start_point,
                                                             r.route_end_point, r.description, br.start_date, br.end_date, br.duration, br.free_places,
                                                             br.price, b.bus_number, r.route_number
                                                      from user_routes ur,
                                                           routes r,
                                                           bus_routes br,
                                                           busses b
                                                      where ur.user_id = :user_id
                                                      and ur.route_id = br.id
                                                      and br.bus_id = b.bus_id
                                                      and br.route_id = r.route_id
                                                      """, namedParameters, new UserRoutesDTOMapper());
    }
}
