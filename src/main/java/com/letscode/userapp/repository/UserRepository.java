package com.letscode.userapp.repository;

import com.letscode.userapp.document.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, String> {

}
