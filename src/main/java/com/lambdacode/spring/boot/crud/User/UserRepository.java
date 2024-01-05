package com.lambdacode.spring.boot.crud.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findById(Long userId);

    default User getUserWithEnrolledCourses(Integer id) {
        User user = findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user Id:" + id));
        user.getEnrolledCourses().size(); // Load courses eagerly
    
        return user;
    }
}
