package com.bus.routes.busroutesapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Route
      implements Serializable {
    
    private Long routeId;
    
    private Long routeNumber;
    
    private String description;
    
    private String routeStartPoint;
    
    private String routeEndPoint;
    
}
