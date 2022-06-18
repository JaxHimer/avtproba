package com.bus.routes.busroutesapp.config;

import com.bus.routes.busroutesapp.BusRoutesAppApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer
      extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BusRoutesAppApplication.class);
    }
}
