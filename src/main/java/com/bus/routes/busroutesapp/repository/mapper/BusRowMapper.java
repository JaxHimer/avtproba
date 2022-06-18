package com.bus.routes.busroutesapp.repository.mapper;

import com.bus.routes.busroutesapp.model.Bus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BusRowMapper
      implements RowMapper<Bus> {
    @Override
    public Bus mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Bus bus = new Bus();
        
        bus.setBusId(rs.getLong("bus_id"));
        bus.setBusInfo(rs.getString("bus_info"));
        bus.setBusNumber(rs.getString("bus_number"));
        
        return bus;
    }
}
