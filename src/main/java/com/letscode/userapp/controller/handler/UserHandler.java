package com.letscode.userapp.controller.handler;

import com.letscode.userapp.dto.UserRequest;
import com.letscode.userapp.dto.UserResponse;
import com.letscode.userapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserService service;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(service.getAll(), UserResponse.class);
    }
    public Mono<ServerResponse> getById(ServerRequest request){
       String id = request.pathVariable("id");
       return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(service.getUser(id), UserResponse.class);

    }
    public Mono<ServerResponse> createUser(ServerRequest request) {
        final Mono<UserRequest> userMono = request.bodyToMono(UserRequest.class);
        return ServerResponse.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromPublisher(userMono.flatMap(service::create), UserResponse.class));
    }


    public Mono<ServerResponse> updateUser(ServerRequest request) {
        String id = request.pathVariable("id");
        return request.bodyToMono(UserRequest.class)
                .flatMap(user -> Mono.just(service.updateUser(id, Mono.just(user))))
                .flatMap(user -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(user)));
}

    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        return Mono.just(service.delete(request.pathVariable("id")))
                .flatMap(val -> ServerResponse.noContent().build());
    }
}
