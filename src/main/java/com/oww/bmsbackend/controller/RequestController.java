package com.oww.bmsbackend.controller;

import com.oww.bmsbackend.entity.Request;
import com.oww.bmsbackend.service.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/v1/request")
public class RequestController {

    private final RequestService service;

    public RequestController(RequestService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Request>> findAll() {
        final List<Request> requests = service.findAll();
        return requests.isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(requests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> findById(@PathVariable int id) {
        final Optional<Request> request = service.findById(id);
        if (request.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(request.get());
    }

    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        if (request.getId() != 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        service.createRequest(request);
        return ResponseEntity.ok(request);
    }

    @PutMapping
    public ResponseEntity<Request> updateRequest(@RequestBody Request request) {
        if (request.getId() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        service.updateRequest(request);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Request> deleteById(@PathVariable int id) {
        Optional<Request> request = service.findById(id);
        if (request.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok(request.get());
    }
}
