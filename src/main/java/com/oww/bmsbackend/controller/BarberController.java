package com.oww.bmsbackend.controller;

import com.oww.bmsbackend.entity.Barber;
import com.oww.bmsbackend.service.BarberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/barbers")
public class BarberController {

    private final BarberService service;

    public BarberController(BarberService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Barber>> findAll() {
        List<Barber> barbers = service.findAll();
        return barbers.isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(barbers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Barber> findById(@PathVariable("id") int id) {
        final Optional<Barber> barber = service.findById(id);
        if (barber.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(barber.get());
    }

    @PostMapping
    public ResponseEntity<Barber> create(@RequestBody Barber barber) {
        if (barber.getId() != 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        service.createBarber(barber);
        return ResponseEntity.ok(barber);
    }

    @PutMapping
    public ResponseEntity<Barber> update(@RequestBody Barber barber) {
        if (barber.getId() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        service.updateBarber(barber);
        return ResponseEntity.ok(barber);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Barber> deleteById(@PathVariable("id") int id) {
        Optional<Barber> barber = service.findById(id);
        if (barber.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok(barber.get());
    }

}
