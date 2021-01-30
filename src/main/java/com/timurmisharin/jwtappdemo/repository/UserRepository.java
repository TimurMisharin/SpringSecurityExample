package com.timurmisharin.jwtappdemo.repository;

import com.timurmisharin.jwtappdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String name);
}

