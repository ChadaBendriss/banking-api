package com.example.banking.service;

import com.example.banking.dto.AccountCreateRequest;
import com.example.banking.dto.AccountResponse;
import com.example.banking.exception.NotFoundException;
import com.example.banking.model.Account;
import com.example.banking.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository repo;

    // Injection or constructor
    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public AccountResponse create(AccountCreateRequest req) {
        Account a = new Account(UUID.randomUUID(), req.getOwnerName().trim(), req.getInitialBalance());
        repo.save(a);
        return new AccountResponse(a.getId(), a.getOwnerName(), a.getBalance());
    }

    public AccountResponse get(UUID id) {
        Account a = repo.findById(id).orElseThrow(() -> new NotFoundException("Account not found: " + id));
        return new AccountResponse(a.getId(), a.getOwnerName(), a.getBalance());
    }
}
