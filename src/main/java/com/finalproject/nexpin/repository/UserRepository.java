package com.finalproject.nexpin.repository;

import com.finalproject.nexpin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //JPA repositorynin icinde bu metod olmadigi ucun ozumuz yaradiriq. bu metod emaile gore useri tapacaq
    Optional<User> findUserByEmail(String email);

    Boolean existsByUserName(String username);
}
