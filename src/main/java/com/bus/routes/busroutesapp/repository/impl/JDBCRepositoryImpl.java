package com.bus.routes.busroutesapp.repository.impl;

import com.bus.routes.busroutesapp.repository.JDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public abstract class JDBCRepositoryImpl<T>
      implements JDBCRepository<T> {
    protected JdbcTemplate jdbcTemplate;
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    
    @Autowired
    public void setDataSource(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public abstract T create(T newData);
    
    public abstract T update(T updateData);
    
    public abstract void delete(T deleteData);
    
    public abstract T getById(Integer id);
    
    public abstract List<T> getAll();
}
