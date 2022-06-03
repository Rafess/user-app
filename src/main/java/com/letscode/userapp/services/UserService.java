package com.letscode.userapp.services;
import com.letscode.userapp.dto.UserRequest;
import com.letscode.userapp.dto.UserResponse;
import com.letscode.userapp.document.User;
import com.letscode.userapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utils.StringRandom;

import java.time.Duration;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Mono<UserResponse> create(UserRequest userRequest) {
        User user = new User();
        user.setId(StringRandom.randomizeString());
        user.setName(userRequest.getName());
        user.setAddress(userRequest.getAddress());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        repository.save(user);
        return Mono.just(UserResponse.toResponde(user));
    }
    // TODO DESCOBRIR PQ OS METODOS GET  NAO MOSTRAM NADA E O ID NAO APARECE.
    public Flux<UserResponse> getAll() {
        return repository.findAll().map(UserResponse::toResponde);
    }

    public Mono<UserResponse> getUser(String id) {
        return repository.findById(id).map(UserResponse::toResponde);
    }

    public Mono<UserResponse> updateUser(String id, Mono<UserRequest> monoRequest) {
        User user = repository.findById(id).blockOptional(Duration.ofMillis(1500)).orElseThrow();
        UserRequest userRequest = monoRequest.blockOptional(Duration.ofMillis(2000)).orElseThrow();
        user.setName(userRequest.getName());
        user.setAddress(userRequest.getAddress());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        repository.save(user);
        return Mono.just(UserResponse.toResponde(user));
    }
    public Mono<Void> delete(String id){
       return repository.deleteById(id);
    }
}
