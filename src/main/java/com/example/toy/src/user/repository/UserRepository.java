package com.example.toy.src.user.repository;

import com.example.toy.src.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
