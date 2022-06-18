package com.bus.routes.busroutesapp.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoute
      implements Serializable {
    
    private Long id;
    
    private Long userId;
    
    private Long routeId;
    
    private String bookingNumber;
    
    private Long amount;
    
    private String status;
    
}
