package org.example.travora.repository;

import org.example.travora.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , String> {


    Optional<User> findByMail(@Param("mail") String userMail);

}
