package com.example.banking.controller;
//request/response data objects.
import com.example.banking.dto.TransactionResponse;
import com.example.banking.dto.TransferRequest;
import com.example.banking.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

// Listens for HTTP requests, converts java return values to JSON responses
@RestController
// Every endpoint in the controller will start with /api
@RequestMapping("/api")
public class TransferController {

    // Dependency Injection or constructor
    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    // Methods for testing

    // Check if GET works
    @GetMapping("/transfers/ping")
    public String getPing() { 
        return "OK-TRANSFERS"; 
    }

    // Check if POST works
    @PostMapping("/transfers/ping")
    public String postPing() { 
        return "POST-OK"; 
    }

    // Check if body is received correctly
    @PostMapping("/transfers/echo")
    public String echo(@RequestBody(required = false) String body) {
        return body == null ? "NO-BODY" : body;
    }

    // Endpoints

    // Transfer money between accounts, it expects a JSON body, TransferRequest
    @PostMapping("/transfers")
    public TransactionResponse transfer(@RequestBody @Valid TransferRequest req) {
        return service.transfer(req);
    }

    // Transaction history
    @GetMapping("/accounts/{accountId}/transactions")
    public List<TransactionResponse> history(@PathVariable UUID accountId,
                                             @RequestParam(defaultValue = "0") int offset,
                                             @RequestParam(defaultValue = "50") int limit) {
        return service.history(accountId, offset, Math.min(Math.max(limit, 1), 200));
    }
}
