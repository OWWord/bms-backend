package com.oww.bmsbackend.controller;

import com.oww.bmsbackend.entity.Barber;
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
@RequestMapping(value = "/api/v1/barbers")
public class BarberController {

    private final BarberService service;

    public BarberController(BarberService service) {
        this.service = service;
    }

    @GetMapping
    @ApiResponse(
            responseCode = "200",
            description = "Получение всех данных на стрижку",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Barber.class))}
    )
    @ApiResponse(
            responseCode = "404",
            description = "Данные в базе данных отсутствуют",
            content = @Content(mediaType = "text")
    )
    public ResponseEntity<List<Barber>> findAll() {
        List<Barber> barbers = service.findAll();
        return barbers.isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(barbers);
    }

    @GetMapping("/{id}")
    @ApiResponse(
            responseCode = "200",
            description = "Получение данных по id",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Barber.class))}
    )
    @ApiResponse(
            responseCode = "404",
            description = "По данному id данных не обнаружено",
            content = @Content(mediaType = "text")
    )
    public ResponseEntity<Barber> findById(@PathVariable("id") int id) {
        final Optional<Barber> barber = service.findById(id);
        if (barber.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(barber.get());
    }

    @PostMapping
    @ApiResponse(
            responseCode = "200",
            description = "Запись новых данных прошла успешно",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Barber.class))}
    )
    @ApiResponse(
            responseCode = "406",
            description = "Создать новую запись не удалось",
            content = @Content(mediaType = "text")
    )
    public ResponseEntity<Barber> create(@RequestBody Barber barber) {
        if (barber.getId() != 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        service.createBarber(barber);
        return ResponseEntity.ok(barber);
    }

    @PutMapping
    @ApiResponse(
            responseCode = "200",
            description = "Обнвление записи прошли успешно",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Barber.class))}
    )
    @ApiResponse(
            responseCode = "406",
            description = "Обновить запись не удалось",
            content = @Content(mediaType = "text")
    )
    public ResponseEntity<Barber> update(@RequestBody Barber barber) {
        if (barber.getId() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        service.updateBarber(barber);
        return ResponseEntity.ok(barber);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(
            responseCode = "200",
            description = "Удаление записи прошли успешно",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Barber.class))}
    )
    @ApiResponse(
            responseCode = "404",
            description = "Данных по id не найдено",
            content = {@Content(mediaType = "text")}
    )
    public ResponseEntity<Barber> deleteById(@PathVariable("id") int id) {
        Optional<Barber> barber = service.findById(id);
        if (barber.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok(barber.get());
    }
}
