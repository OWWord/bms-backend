package com.oww.bmsbackend.controller;

import com.oww.bmsbackend.entity.Appointment;
import com.oww.bmsbackend.entity.Barber;
import com.oww.bmsbackend.service.AppointmentService;
import com.oww.bmsbackend.service.BarberService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
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
    @ApiResponse(
            responseCode = "200",
            description = "Получение всех данных на стрижку",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Appointment.class))}
    )
    @ApiResponse(
            responseCode = "404",
            description = "Данные в базе данных отсутствуют",
            content = @Content(mediaType = "text")
    )
    public ResponseEntity<List<Appointment>> findAll() {
        List<Appointment> appointments = service.findAll();
        return appointments.isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    @ApiResponse(
            responseCode = "200",
            description = "Получение данных по id",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Appointment.class))}
    )
    @ApiResponse(
            responseCode = "404",
            description = "По данному id данных не обнаружено",
            content = @Content(mediaType = "text")
    )
    public ResponseEntity<Appointment> findById(@PathVariable int id) {
        Optional<Appointment> appointment = service.findById(id);
        if (appointment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(appointment.get());
    }

    @PostMapping
    @ApiResponse(
            responseCode = "200",
            description = "Запись новых данных прошла успешно",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Appointment.class))}
    )
    @ApiResponse(
            responseCode = "406",
            description = "Создать новую запись не удалось",
            content = @Content(mediaType = "text")
    )
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
    @ApiResponse(
            responseCode = "200",
            description = "Обнвление записи прошли успешно",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Appointment.class))}
    )
    @ApiResponse(
            responseCode = "406",
            description = "Обновить запись не удалось",
            content = @Content(mediaType = "text")
    )
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
    @ApiResponse(
            responseCode = "200",
            description = "Удаление записи прошли успешно",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Appointment.class))}
    )
    @ApiResponse(
            responseCode = "404",
            description = "Данных по id не найдено",
            content = @Content(mediaType = "text")
    )
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable int id) {
        Optional<Appointment> appointment = service.findById(id);
        if (appointment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok(appointment.get());
    }
}
