package com.example.banking.repository.impl;

import com.example.banking.model.Account;
import com.example.banking.repository.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryAccountRepository implements AccountRepository {

    private final Map<UUID, Account> store = new ConcurrentHashMap<>();

    @Override
    public Account save(Account a) {
        store.put(a.getId(), a);
        return a;
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }
}
