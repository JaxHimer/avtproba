package com.bus.routes.busroutesapp.repository.mapper;

import com.bus.routes.busroutesapp.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper
      implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        final User user = new User();
        
        user.setUserId(rs.getLong("user_id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setMiddleName(rs.getString("middle_name"));
        user.setRoleId(rs.getLong("role_id"));
        user.setEmailSubmitted(rs.getBoolean("email_submitted"));
        return user;
    }
}
