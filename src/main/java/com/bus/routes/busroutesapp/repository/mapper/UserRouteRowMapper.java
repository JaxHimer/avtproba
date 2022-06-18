package com.bus.routes.busroutesapp.repository.mapper;

import com.bus.routes.busroutesapp.model.UserRoute;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRouteRowMapper
      implements RowMapper<UserRoute> {
    @Override
    public UserRoute mapRow(ResultSet rs, int rowNum) throws SQLException {
        final UserRoute userRoute = new UserRoute();
        
        userRoute.setId(rs.getLong("id"));
        userRoute.setRouteId(rs.getLong("route_id"));
        userRoute.setUserId(rs.getLong("user_id"));
        userRoute.setBookingNumber(rs.getString("booking_number"));
        userRoute.setAmount(rs.getLong("amount"));
        userRoute.setStatus(rs.getString("status"));
        return userRoute;
    }
}
