package com.oww.bmsbackend.controller;

import com.oww.bmsbackend.entity.ClientHistory;
import com.oww.bmsbackend.service.ClientHistoryService;
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
@RequestMapping(value = "/api/v1/history")
public class ClientHistoryController {

    private final ClientHistoryService service;

    public ClientHistoryController(ClientHistoryService service) {
        this.service = service;
    }

    @GetMapping
    @ApiResponse(
            responseCode = "200",
            description = "Получение всех данных на стрижку",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ClientHistory.class))}
    )
    @ApiResponse(
            responseCode = "404",
            description = "Данные в базе данных отсутствуют",
            content = @Content(mediaType = "text")
    )
    public ResponseEntity<List<ClientHistory>> findAll() {
        final List<ClientHistory> clientHistories = service.findAll();
        return clientHistories.isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(clientHistories);
    }

    @GetMapping("/{id}")
    @ApiResponse(
            responseCode = "200",
            description = "Получение данных по id",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ClientHistory.class))}
    )
    @ApiResponse(
            responseCode = "404",
            description = "По данному id данных не обнаружено",
            content = @Content(mediaType = "text")
    )
    public ResponseEntity<ClientHistory> findById(@PathVariable int id) {
        final Optional<ClientHistory> clientHistory = service.findById(id);
        return clientHistory.isPresent() ?
                ResponseEntity.ok(clientHistory.get()) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    @ApiResponse(
            responseCode = "200",
            description = "Запись новых данных прошла успешно",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ClientHistory.class))}
    )
    @ApiResponse(
            responseCode = "406",
            description = "Создать новую запись не удалось",
            content = @Content(mediaType = "text")
    )
    public ResponseEntity<ClientHistory> createClientHistory(@RequestBody ClientHistory clientHistory) {
        return clientHistory.getId() != 0 ?
                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build() :
                ResponseEntity.ok(service.createClientHistory(clientHistory));
    }

    @PutMapping
    @ApiResponse(
            responseCode = "200",
            description = "Обнвление записи прошли успешно",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ClientHistory.class))}
    )
    @ApiResponse(
            responseCode = "406",
            description = "Обновить запись не удалось",
            content = @Content(mediaType = "text")
    )
    public ResponseEntity<ClientHistory> updateClientHistory(@RequestBody ClientHistory clientHistory) {
        return clientHistory.getId() == 0 ?
                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build() :
                ResponseEntity.ok(service.createClientHistory(clientHistory));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(
            responseCode = "200",
            description = "Удаление записи прошли успешно",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ClientHistory.class))}
    )
    @ApiResponse(
            responseCode = "404",
            description = "Данных по id не найдено",
            content = @Content(mediaType = "text")
    )
    public ResponseEntity<ClientHistory> deleteById(@PathVariable int id) {
        Optional<ClientHistory> clientHistory = service.findById(id);
        if (clientHistory.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok(clientHistory.get());
        }
        return ResponseEntity.notFound().build();
    }
}
