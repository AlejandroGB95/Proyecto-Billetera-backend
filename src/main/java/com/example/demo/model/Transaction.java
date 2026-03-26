package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private String type; 
    private String category; 

    @ManyToOne(fetch = FetchType.EAGER) // Forzamos la carga del usuario si es necesario
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("transactions") // <--- Evita que el JSON regrese al usuario y cree un bucle
    private User user;
}
