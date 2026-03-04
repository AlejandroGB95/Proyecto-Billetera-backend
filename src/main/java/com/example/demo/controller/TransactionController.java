package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    // 1. Ponemos todas las inyecciones juntas arriba (Buenas prácticas)
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    // Crear una transacción
    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.saveTransaction(transaction));
    }

    // Historial por usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> getHistory(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return ResponseEntity.ok(transactionService.getTransactionsByUser(user));
    }

    // Ver todas
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.findAll(); 
    }

    // Balances
    @GetMapping("/user/{userId}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.calculateBalance(userId));
    }

    @GetMapping("/user/{userId}/balance/{year}/{month}")
    public ResponseEntity<BigDecimal> getBalanceByMonth(
            @PathVariable Long userId,
            @PathVariable int year,
            @PathVariable int month) {
        return ResponseEntity.ok(transactionService.getBalanceByMonth(userId, year, month));
    }

    @GetMapping("/user/{userId}/expenses-by-category")
    public ResponseEntity<Map<String, BigDecimal>> getExpensesByCategory(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.getExpensesByCategory(userId));
    }

    // MÉTODO DE BORRADO CORREGIDO
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            if (transactionRepository.existsById(id)) {
                transactionRepository.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(404).body("No existe el ID " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}