package com.bus.routes.busroutesapp.repository;

import java.util.List;

public interface JDBCRepository<T> {
    T create(T newData);
    
    T update(T updateData);
    
    void delete(T deleteData);
    
    T getById(Integer id);
    List<T> getAll();
}
