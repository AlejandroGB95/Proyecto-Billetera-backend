package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // <--- No olvides este import
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("transactions") // <--- ESTO CORTA EL BUCLE DESDE ESTE LADO
    private User user;
}