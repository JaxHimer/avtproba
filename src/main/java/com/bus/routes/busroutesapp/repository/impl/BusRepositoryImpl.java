package com.bus.routes.busroutesapp.repository.impl;

import com.bus.routes.busroutesapp.model.Bus;
import com.bus.routes.busroutesapp.repository.mapper.BusRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

//Класс отвечающий за отправку запросов в базу данных
@Repository
public class BusRepositoryImpl
      extends JDBCRepositoryImpl<Bus> {
    
    //формирует запрос и выполняет его подставляя данные в параметры
    @Override
    public Bus create(Bus newData) {
        //Запрос
        String query = "insert into busses(bus_number, bus_info) values (?, ?)";
        //Выполнение запроса и запись id созданной записи в переменную
        Integer id = jdbcTemplate.update(query, newData.getBusNumber(), newData.getBusInfo());
        //Установка полученного id для объекта который мы записали в базу
        newData.setBusId(id.longValue());
        return newData;
    }
    
    @Override
    public Bus update(Bus updateData) {
        String query = """
              update busses set bus_number = ?, bus_info = ? where bus_id = ?
              """;
        jdbcTemplate.update(query, updateData.getBusInfo(), updateData.getBusNumber(), updateData.getBusId());
        return updateData;
    }
    
    @Override
    public void delete(Bus deleteData) {
        String query = """
              delete from busses where bus_id = ?""";
        jdbcTemplate.update(query, deleteData.getBusId());
    }
    
    @Override
    public Bus getById(Integer id) {
        //Передача параметров в запрос ()
        final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("bus_id", id);
        return namedParameterJdbcTemplate.queryForObject("select * from busses where bus_id = :bus_id", namedParameters, new BusRowMapper());
    }
    
    @Override
    public List<Bus> getAll() {
        return jdbcTemplate.query("SELECT * FROM busses", new BusRowMapper());
    }
}
