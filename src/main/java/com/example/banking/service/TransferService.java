package com.example.banking.service;

import com.example.banking.dto.TransactionResponse;
import com.example.banking.dto.TransferRequest;
import com.example.banking.model.Account;
import com.example.banking.model.Transaction;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import com.example.banking.exception.NotFoundException;
import com.example.banking.exception.InsufficientFundsException;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class TransferService {

    private final AccountRepository accounts;
    private final TransactionRepository txs;

    public TransferService(AccountRepository accounts, TransactionRepository txs) {
        this.accounts = accounts;
        this.txs = txs;
    }

    public TransactionResponse transfer(TransferRequest req) {

        // The person can’t transfer from an account to itself.
        if (req.getFromAccountId().equals(req.getToAccountId())) {
            throw new IllegalArgumentException("fromAccountId must differ from toAccountId");
        }

        // Load both accounts
        Account from = accounts.findById(req.getFromAccountId())
                .orElseThrow(() -> new NotFoundException("Source account not found"));
        Account to = accounts.findById(req.getToAccountId())
                .orElseThrow(() -> new NotFoundException("Destination account not found"));

        // Lock both accounts to avoid deadlocks
        var ordered = List.of(from, to).stream().sorted(Comparator.comparing(Account::getId)).toList();

        ordered.get(0).getLock().lock();
        ordered.get(1).getLock().lock();
        try {
            if (from.getBalance().compareTo(req.getAmount()) < 0) {
                throw new InsufficientFundsException("Insufficient funds");
            }

            from.setBalance(from.getBalance().subtract(req.getAmount()));
            to.setBalance(to.getBalance().add(req.getAmount()));

            Transaction t = new Transaction(
                    UUID.randomUUID(),
                    from.getId(),
                    to.getId(),
                    req.getAmount(),
                    Transaction.Type.TRANSFER,
                    Instant.now()
            );
            txs.save(t);
            return TransactionResponse.from(t);
        } finally {
            ordered.get(1).getLock().unlock();
            ordered.get(0).getLock().unlock();
        }
    }

    public List<TransactionResponse> history(UUID accountId, int offset, int limit) {
        accounts.findById(accountId).orElseThrow(() -> new NotFoundException("Account not found"));
        return txs.findByAccountId(accountId, offset, limit).stream().map(TransactionResponse::from).toList();
    }
}
