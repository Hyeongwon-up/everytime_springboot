package com.example.toy.src.user.repository;

import com.example.toy.src.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    boolean existsUserByUser_id(String user_id); //find~와 exists~ 함수는 카멜 케이스만 가능
    boolean existsUserById(String id);
    boolean existsUserByNickname(String nickname);

    Optional<User> findUserByIdAndPassword(String id, String password);
}
