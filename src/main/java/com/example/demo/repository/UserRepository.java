package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Esto nos permitirá buscar usuarios por su nombre al hacer login
    Optional<User> findByUsername(String username);
    Optional<User> findByEmailAndPassword(String email, String password);
    
}