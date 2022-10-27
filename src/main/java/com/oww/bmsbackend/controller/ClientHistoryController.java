package com.oww.bmsbackend.controller;

import com.oww.bmsbackend.entity.ClientHistory;
import com.oww.bmsbackend.service.ClientHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/history")
public class ClientHistoryController {

    private final ClientHistoryService service;

    public ClientHistoryController(ClientHistoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ClientHistory>> findAll() {
        final List<ClientHistory> clientHistories = service.findAll();
        return clientHistories.isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(clientHistories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientHistory> findById(@PathVariable int id) {
        final Optional<ClientHistory> clientHistory = service.findById(id);
        return clientHistory.isPresent() ?
                ResponseEntity.ok(clientHistory.get()) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ClientHistory> createClientHistory(@RequestBody ClientHistory clientHistory) {
        return clientHistory.getId() != 0 ?
            ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build() :
            ResponseEntity.ok(service.createClientHistory(clientHistory));
    }

    @PutMapping
    public ResponseEntity<ClientHistory> updateClientHistory(@RequestBody ClientHistory clientHistory) {
        return clientHistory.getId() == 0 ?
                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build() :
                ResponseEntity.ok(service.createClientHistory(clientHistory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientHistory> deleteById(@PathVariable int id) {
        Optional<ClientHistory> clientHistory = service.findById(id);
        if (clientHistory.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok(clientHistory.get());
        }
        return ResponseEntity.notFound().build();
    }

}
