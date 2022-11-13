package com.oww.bmsbackend.controller;

import com.oww.bmsbackend.entity.ClientHistory;
import com.oww.bmsbackend.service.ClientHistoryService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/history")
public class ClientHistoryController {

    private final ClientHistoryService clientHistoryService;

    public ClientHistoryController(ClientHistoryService clientHistoryService) {
        this.clientHistoryService = clientHistoryService;
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
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<List<ClientHistory>> findAll() {
        return ResponseEntity.ok(clientHistoryService.findAll());
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
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<ClientHistory> findById(@PathVariable int id) {
        return ResponseEntity.ok(clientHistoryService.findById(id));
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
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<ClientHistory> createClientHistory(@RequestBody ClientHistory clientHistory) {
        clientHistoryService.createClientHistory(clientHistory);
        return ResponseEntity.ok(clientHistory);
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
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<ClientHistory> updateClientHistory(@RequestBody ClientHistory clientHistory) {
        clientHistoryService.updateClientHistory(clientHistory);
        return ResponseEntity.ok(clientHistory);
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
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<ClientHistory> deleteById(@PathVariable int id) {
        clientHistoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
