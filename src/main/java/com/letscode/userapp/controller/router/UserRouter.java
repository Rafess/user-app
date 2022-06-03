package com.letscode.userapp.controller.router;

import com.letscode.userapp.controller.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> router(UserHandler handler) {

       return route().path("/usuarios", u -> u.nest(accept(MediaType.APPLICATION_JSON), u2 -> u2.GET("/{id}", handler::getById).GET("", handler::getAll).POST("", handler::createUser).PUT("/update/{id}", handler::updateUser).DELETE("/delete/{id}", handler::deleteUser))).build();

    }
} 
