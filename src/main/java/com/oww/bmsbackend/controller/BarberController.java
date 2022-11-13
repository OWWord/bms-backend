package com.oww.bmsbackend.controller;

import com.oww.bmsbackend.entity.Barber;
import com.oww.bmsbackend.service.BarberService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/barbers")
public class BarberController {

    private final BarberService barberService;

    public BarberController(BarberService barberService) {
        this.barberService = barberService;
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
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<List<Barber>> findAll() {
        return ResponseEntity.ok(barberService.findAll());
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
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<Barber> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(barberService.findById(id));
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
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<Barber> create(@RequestBody Barber barber) {
        barberService.createBarber(barber);
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
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<Barber> update(@RequestBody Barber barber) {
        barberService.updateBarber(barber);
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
            content = {@Content(mediaType = APPLICATION_JSON_VALUE)}
    )
    public ResponseEntity<Barber> deleteById(@PathVariable("id") int id) {
        barberService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
