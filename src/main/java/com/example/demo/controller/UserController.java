package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) { // Cambiamos <User> por <?> para enviar texto si falla
        try {
            User savedUser = userService.registerUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (RuntimeException e) {
            // En lugar de build(), enviamos el mensaje de la excepción
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User loginUser) {
    try {
        User user = userService.loginUser(loginUser.getEmail(), loginUser.getPassword());
        return ResponseEntity.ok(user);
    } catch (RuntimeException e) {
        return ResponseEntity.status(401).body(e.getMessage());
    }
}
}