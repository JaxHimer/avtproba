package com.bus.routes.busroutesapp.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Data
public class Role
      implements Serializable, GrantedAuthority {
    
    private Long roleId;
    
    private String name;
    
    private String description;
    
    public Role(Long roleId) {
        this.roleId = roleId;
    }
    
    public Role(Long roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }
    
    @Override
    public String getAuthority() {
        return getName();
    }
}
