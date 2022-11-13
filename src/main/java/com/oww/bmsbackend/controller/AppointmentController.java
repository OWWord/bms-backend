package com.oww.bmsbackend.controller;

import com.oww.bmsbackend.entity.Appointment;
import com.oww.bmsbackend.service.AppointmentService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
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
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<List<Appointment>> findAll() {
        return ResponseEntity.ok(appointmentService.findAll());
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
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<Appointment> findById(@PathVariable int id) {
        return ResponseEntity.ok(appointmentService.findById(id));
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
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        appointmentService.createAppointment(appointment);
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
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment) {
        appointmentService.updateAppointment(appointment);
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
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable int id) {
        appointmentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
