package com.bus.routes.busroutesapp.repository.impl;

import com.bus.routes.busroutesapp.model.User;
import com.bus.routes.busroutesapp.repository.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl
      extends JDBCRepositoryImpl<User> {
    
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    @Override
    public User create(User newData) {
        System.out.println("USER: " + newData);
        System.out.println("ROLE:" + newData.getRoleId());
        String query = """
              insert into users(email, password, first_name, last_name, middle_name, email_submitted, role_id)
              values (?, ?, ?, ?, ?, ?, ?)""";
        Integer id = jdbcTemplate.update(query,
                                         newData.getEmail(),
                                         bCryptPasswordEncoder.encode(newData.getPassword()),
                                         newData.getFirstName(),
                                         newData.getLastName(),
                                         newData.getMiddleName(),
                                         newData.getEmailSubmitted(),
                                         newData.getRoleId());
        newData.setUserId(id.longValue());
        return newData;
    }
    
    @Override
    public User update(User updateData) {
        String query = """
              update users set email = ?, password = ?, first_name = ?,  last_name = ?, middle_name = ?, email_submitted = ?, role_id = ?
              where user_id = ?
              """;
        jdbcTemplate.update(query, updateData.getEmail(), bCryptPasswordEncoder.encode(updateData.getPassword()),
                            updateData.getFirstName(), updateData.getLastName(), updateData.getMiddleName(),
                            updateData.getEmailSubmitted(), updateData.getRoleId(), updateData.getUserId());
        return updateData;
    }
    
    @Override
    public void delete(User deleteData) {
        String query = """
              delete from users where user_id = ?""";
        jdbcTemplate.update(query, deleteData.getUserId());
    }
    
    @Override
    public User getById(Integer id) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("user_id", id);
        return namedParameterJdbcTemplate.queryForObject("select * from users where user_id = :user_id", namedParameters, new UserRowMapper());
    }
    
    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
    }
    
    public List<User> getByRoleId(Integer roleId) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("role_id", roleId);
        return namedParameterJdbcTemplate.query("select * from users where role_id = :role_id", namedParameters, new UserRowMapper());
    }
    
    public User findByUsername(final String userName) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("email", userName);
        return namedParameterJdbcTemplate.queryForObject("select * from users where email = :email", namedParameters, new UserRowMapper());
    }
}
