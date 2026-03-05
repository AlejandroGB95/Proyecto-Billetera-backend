package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Método para registrar un nuevo usuario
    public User registerUser(User user) {
        // Aquí podrías añadir lógica para encriptar la contraseña más adelante
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        return userRepository.save(user);
    }

    // Método para buscar un usuario por su ID
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Añade esto al final de tu clase UserService.java
    public User loginUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
            .orElseThrow(() -> new RuntimeException("Email o contraseña incorrectos"));
    }
}