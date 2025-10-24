package com.example.banking.repository;

import com.example.banking.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository {
    Transaction save(Transaction t);
    List<Transaction> findByAccountId(UUID accountId, int offset, int limit);
}
