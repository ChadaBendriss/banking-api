package com.example.banking.dto;

import com.example.banking.model.Transaction;

import java.math.BigDecimal;
import java.time.Instant;

// unique identifiers for accounts and transactions
import java.util.UUID;

public class TransactionResponse {
    private UUID id;
    private UUID fromAccountId;
    private UUID toAccountId;
    private BigDecimal amount;
    private Transaction.Type type;
    private Instant createdAt;

    // Constructor
    public TransactionResponse(UUID id, UUID fromAccountId, UUID toAccountId, BigDecimal amount, Transaction.Type type, Instant createdAt) {
        this.id = id; 
        this.fromAccountId = fromAccountId; 
        this.toAccountId = toAccountId;
        this.amount = amount; 
        this.type = type; 
        this.createdAt = createdAt;
    }

    public static TransactionResponse from(Transaction t){
        return new TransactionResponse(t.getId(), t.getFromAccountId(), t.getToAccountId(), t.getAmount(), t.getType(), t.getCreatedAt());
    }

    // getters
    public UUID getId() { 
        return id; 
    }

    public UUID getFromAccountId() { 
        return fromAccountId;
    }

    public UUID getToAccountId() { 
        return toAccountId; 
    }

    public BigDecimal getAmount() { 
        return amount; 
    }

    public Transaction.Type getType() { 
        return type; 
    }

    public Instant getCreatedAt() { 
        return createdAt; 
    }
}
