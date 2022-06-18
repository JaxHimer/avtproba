package com.bus.routes.busroutesapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

//Класс с помощью которого происходит объединение данных который потом отправляется на фронт
@Data // автоматически генерит конструкторы, геттеры сеттеры
@NoArgsConstructor
public class UserRoutesDTO {
    private Long userRouteId;
    private Long userId;
    private RouteDTO route;
    private String bookingNum;
    private Long amount;
    private String status;
}
