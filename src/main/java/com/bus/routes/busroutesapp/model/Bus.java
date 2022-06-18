package com.bus.routes.busroutesapp.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Bus
      implements Serializable {
    
    private Long busId;
    
    private String busNumber;
    
    private String busInfo;
    
}
