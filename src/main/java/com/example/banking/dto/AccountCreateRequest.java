// This file represents the data a user sends to create a new account
package com.example.banking.dto;

// Imports for validation
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// For precision, BigDecimal 
import java.math.BigDecimal;

public class AccountCreateRequest {
    @NotBlank(message = "ownerName is required")
    private String ownerName;

    @NotNull(message = "initialBalance is required")
    @DecimalMin(value = "0.00", inclusive = true, message = "initialBalance must be >= 0")
    private BigDecimal initialBalance;

    // getters 
    public String getOwnerName() { 
        return ownerName; 
    }

    public BigDecimal getInitialBalance() { 
        return initialBalance; 
    }

    // setters
    public void setOwnerName(String ownerName) { 
        this.ownerName = ownerName; 
    }

    public void setInitialBalance(BigDecimal initialBalance) { 
        this.initialBalance = initialBalance; 
    }
}
