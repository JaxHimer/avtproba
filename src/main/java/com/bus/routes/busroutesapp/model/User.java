package com.bus.routes.busroutesapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class User
      implements Serializable {
    
    private Long userId;
    
    private String email;
    
    private String password;
    
    private String firstName;
    
    private String lastName;
    
    private String middleName;
    
    private Boolean emailSubmitted;
    
    private Long roleId;
    
}
