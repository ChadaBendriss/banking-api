package com.example.banking.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Transaction {
    public enum Type { TRANSFER }

    private UUID id;
    private UUID fromAccountId;
    private UUID toAccountId;
    private BigDecimal amount;
    private Type type;
    private Instant createdAt;

    // Constructor
    public Transaction(UUID id, UUID fromAccountId, UUID toAccountId, BigDecimal amount, Type type, Instant createdAt) {
        this.id = id;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.type = type;
        this.createdAt = createdAt;
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

    public Type getType() { 
        return type; 
    }
    
    public Instant getCreatedAt() { 
        return createdAt; 
    }
}
