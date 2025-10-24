package com.example.banking.controller;

import com.example.banking.dto.AccountCreateRequest;
import com.example.banking.dto.AccountResponse;
import com.example.banking.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    // Create an account
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public AccountResponse create(@RequestBody @Valid AccountCreateRequest request) {
        return service.create(request);
    }

    // Get an account by id
    @GetMapping("/{id}")
    public AccountResponse get(@PathVariable UUID id) {
        return service.get(id);
    }
}
