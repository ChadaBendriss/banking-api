package com.example.banking.model;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private UUID id;
    private String ownerName;
    private BigDecimal balance;
    // lock accounts for safe transfers
    private transient ReentrantLock lock = new ReentrantLock();

    // Constructor
    public Account(UUID id, String ownerName, BigDecimal balance) {
        this.id = id;
        this.ownerName = ownerName;
        this.balance = balance;
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
    public ReentrantLock getLock() { 
        return lock; 
    }

    // setters
    public void setOwnerName(String ownerName) { 
        this.ownerName = ownerName; 
    }

    public void setBalance(BigDecimal balance) { 
        this.balance = balance; 
    }
}
