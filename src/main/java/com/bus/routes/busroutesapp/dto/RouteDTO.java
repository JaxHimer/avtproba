package com.bus.routes.busroutesapp.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

//Класс с помощью которого происходит объединение данных который потом отправляется на фронт
@Data
public class RouteDTO implements Serializable {
    private Long routeId;
    private Long routeNum;
    private String description;
    private String routeStart;
    private String routeEnd;
    private Long busId;
    private String busNumber;
    private Date startDate;
    private Date endDate;
    private String duration;
    private Long freePlaceAmount;
    private String price;
    private Long busRouteId;
}
