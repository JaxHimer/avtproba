package com.bus.routes.busroutesapp.service;

import java.util.List;

public interface MyService<T> {
    T create(T data);
    
    T update(T data);
    
    void delete(T data);
    
    T getOne(Integer id);
    
    List<T> getAll();
}
