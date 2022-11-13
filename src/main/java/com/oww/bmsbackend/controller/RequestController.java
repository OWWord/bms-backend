package com.oww.bmsbackend.controller;

import com.oww.bmsbackend.entity.Request;
import com.oww.bmsbackend.service.RequestService;
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
@RequestMapping(value = "/api/v1/request")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    @ApiResponse(
            responseCode = "200",
            description = "Получение всех данных на стрижку",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Request.class))}
    )
    @ApiResponse(
            responseCode = "404",
            description = "Данные в базе данных отсутствуют",
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<List<Request>> findAll() {
        return ResponseEntity.ok(requestService.findAll());
    }

    @GetMapping("/{id}")
    @ApiResponse(
            responseCode = "200",
            description = "Получение данных по id",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Request.class))}
    )
    @ApiResponse(
            responseCode = "404",
            description = "По данному id данных не обнаружено",
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<Request> findById(@PathVariable int id) {
        return ResponseEntity.ok(requestService.findById(id));
    }

    @PostMapping
    @ApiResponse(
            responseCode = "200",
            description = "Запись новых данных прошла успешно",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Request.class))}
    )
    @ApiResponse(
            responseCode = "406",
            description = "Создать новую запись не удалось",
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        requestService.createRequest(request);
        return ResponseEntity.ok(request);
    }

    @PutMapping
    @ApiResponse(
            responseCode = "200",
            description = "Обнвление записи прошли успешно",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Request.class))}
    )
    @ApiResponse(
            responseCode = "406",
            description = "Обновить запись не удалось",
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<Request> updateRequest(@RequestBody Request request) {
        requestService.updateRequest(request);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(
            responseCode = "200",
            description = "Удаление записи прошли успешно",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Request.class))}
    )
    @ApiResponse(
            responseCode = "404",
            description = "Данных по id не найдено",
            content = @Content(mediaType = APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<Request> deleteById(@PathVariable int id) {
        requestService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
