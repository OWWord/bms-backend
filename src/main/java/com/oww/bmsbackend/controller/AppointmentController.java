package com.oww.bmsbackend.controller;

import com.oww.bmsbackend.entity.Appointment;
import com.oww.bmsbackend.entity.Barber;
import com.oww.bmsbackend.service.AppointmentService;
import com.oww.bmsbackend.service.BarberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/v1/appointment")
public class AppointmentController {

    private AppointmentService service;
    private BarberService barberService;

    public AppointmentController(AppointmentService service,
                                 BarberService barberService) {
        this.service = service;
        this.barberService = barberService;
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> findAll() {
        List<Appointment> appointments = service.findAll();
        return appointments.isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> findById(@PathVariable int id) {
        Optional<Appointment> appointment = service.findById(id);
        if (appointment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(appointment.get());
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Optional<Barber> barber = barberService.
                findById(appointment.getBarber().getId());
        if (barber.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        if (appointment.getId() != 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        service.createAppointment(appointment);
        return ResponseEntity.ok(appointment);
    }

    @PutMapping
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment) {
        Optional<Barber> barber = barberService.
                findById(appointment.getBarber().getId());
        if (barber.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        if (appointment.getId() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        service.updateAppointment(appointment);
        return ResponseEntity.ok(appointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable int id) {
        Optional<Appointment> appointment = service.findById(id);
        if (appointment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok(appointment.get());
    }
}
