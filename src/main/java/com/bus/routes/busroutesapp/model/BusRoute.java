package com.bus.routes.busroutesapp.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class BusRoute
      implements Serializable {
    
    private Long id;
    
    private Long busId;
    
    private Long routeId;
    
    private Date startDate;
    
    private Date endDate;
    
    private String duration;
    
    private Long freePlaces;
    
    private String price;
    
}
