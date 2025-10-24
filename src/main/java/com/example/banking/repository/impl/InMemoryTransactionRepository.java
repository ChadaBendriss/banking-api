package com.example.banking.repository.impl;

import com.example.banking.model.Transaction;
import com.example.banking.repository.TransactionRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryTransactionRepository implements TransactionRepository {

    private final Map<UUID, Transaction> store = new ConcurrentHashMap<>();
    private final Map<UUID, List<UUID>> indexByAccount = new ConcurrentHashMap<>();

    @Override
    public Transaction save(Transaction t) {
        store.put(t.getId(), t);

        if (t.getFromAccountId() != null) {
            indexByAccount.computeIfAbsent(t.getFromAccountId(), k -> Collections.synchronizedList(new ArrayList<>())).add(t.getId());
        }
        if (t.getToAccountId() != null) {
            indexByAccount.computeIfAbsent(t.getToAccountId(), k -> Collections.synchronizedList(new ArrayList<>())).add(t.getId());
        }
        return t;
    }

    @Override
    public List<Transaction> findByAccountId(UUID accountId, int offset, int limit) {
        List<UUID> ids = indexByAccount.getOrDefault(accountId, List.of());
        List<UUID> rev = new ArrayList<>(ids);
        Collections.reverse(rev); 

        int from = Math.max(0, Math.min(offset, rev.size()));
        int to = Math.max(from, Math.min(from + limit, rev.size()));

        List<Transaction> out = new ArrayList<>();
        for (UUID id : rev.subList(from, to)) {
            out.add(store.get(id));
        }
        return out;
    }
}
