package com.example.banking.dto;

// imports for validation
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public class TransferRequest {
    @NotNull(message = "fromAccountId is required")
    private UUID fromAccountId;

    @NotNull(message = "toAccountId is required")
    private UUID toAccountId;

    @NotNull(message = "amount is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "amount must be > 0")
    private BigDecimal amount;

    // getters
    public UUID getFromAccountId() { 
        return fromAccountId; 
    }

    public UUID getToAccountId() { 
        return toAccountId; 
    }

    public BigDecimal getAmount() { 
        return amount; 
    }

    // setters
    public void setFromAccountId(UUID fromAccountId) { 
        this.fromAccountId = fromAccountId; 
    }

    public void setToAccountId(UUID toAccountId) { 
        this.toAccountId = toAccountId; 
    }

    public void setAmount(BigDecimal amount) { 
        this.amount = amount; 
    }
}
