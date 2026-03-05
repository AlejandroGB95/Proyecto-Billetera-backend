package com.example.demo.service;

import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal; // Importante para comparar el dinero
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction) {
        // VALIDACIÓN: Si el monto es menor o igual a cero, lanzamos un error
        if (transaction.getAmount() == null || transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("El monto de la transacción debe ser mayor a cero");
        }

        // Si pasa la validación, se guarda
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findAll() {
    return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByUser(User user) {
        return transactionRepository.findByUser(user);
    }

    public BigDecimal calculateBalance(Long userId) {
    User user = new User();
    user.setId(userId);
    List<Transaction> transactions = transactionRepository.findByUser(user);

    BigDecimal balance = BigDecimal.ZERO;

    for (Transaction t : transactions) {
        if ("INCOME".equalsIgnoreCase(t.getType())) {
            balance = balance.add(t.getAmount());
        } else if ("EXPENSE".equalsIgnoreCase(t.getType())) {
            balance = balance.subtract(t.getAmount());
        }
    }
    return balance;

    }

    public BigDecimal getBalanceByMonth(Long userId, int year, int month) {
    User user = new User();
    user.setId(userId);
    
    // Calculamos el primer y último día del mes
    LocalDate start = LocalDate.of(year, month, 1);
    LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

    List<Transaction> transactions = transactionRepository.findByUserAndDateBetween(user, start, end);
    
    BigDecimal balance = BigDecimal.ZERO;
    for (Transaction t : transactions) {
        if ("INCOME".equalsIgnoreCase(t.getType())) balance = balance.add(t.getAmount());
        else if ("EXPENSE".equalsIgnoreCase(t.getType())) balance = balance.subtract(t.getAmount());
    }
    return balance;
    }

    public java.util.Map<String, BigDecimal> getExpensesByCategory(Long userId) {
    User user = new User();
    user.setId(userId);
    List<Transaction> transactions = transactionRepository.findByUser(user);

    return transactions.stream()
        .filter(t -> "EXPENSE".equalsIgnoreCase(t.getType())) // Solo gastos
        .collect(java.util.stream.Collectors.groupingBy(
            Transaction::getCategory,
            java.util.stream.Collectors.reducing(
                BigDecimal.ZERO,
                Transaction::getAmount,
                BigDecimal::add
            )
        ));
    }

    public void delete(Long id) {
    transactionRepository.deleteById(id);
}
    
}