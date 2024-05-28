package com.example.TastyKing.Repository;

import com.example.TastyKing.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    boolean existsByEmail(String email);

    Optional<Users> findByEmail(String email);
}
