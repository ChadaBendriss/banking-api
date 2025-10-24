package com.example.banking.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountResponse {
    private UUID id;
    private String ownerName;
    private BigDecimal balance;

    public AccountResponse(UUID id, String ownerName, BigDecimal balance) {
        this.id = id; this.ownerName = ownerName; this.balance = balance;
    }

    // getters
    public UUID getId() { 
        return id; 
    }

    public String getOwnerName() { 
        return ownerName; 
    }

    public BigDecimal getBalance() { 
        return balance; 
    }
}
