package unaldi.logservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unaldi.logservice.model.dto.LogDTO;
import unaldi.logservice.model.request.LogRequest;
import unaldi.logservice.model.response.LogResponse;
import unaldi.logservice.service.abstracts.LogService;
import unaldi.logservice.utils.result.DataResult;
import unaldi.logservice.utils.result.Result;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@RestController
@RequestMapping("api/v1/logs")
public class LogController {
    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping
    public ResponseEntity<DataResult<LogResponse>> sendToLog(@RequestBody LogRequest logRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.logService.sendToLog(logRequest));
    }

    @DeleteMapping("/{logId}")
    public ResponseEntity<Result> deleteById(@PathVariable String logId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.logService.deleteById(logId));
    }

    @GetMapping("/{logId}")
    public ResponseEntity<DataResult<LogDTO>> findById(@PathVariable String logId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.logService.findById(logId));
    }

    @GetMapping
    public ResponseEntity<DataResult<List<LogDTO>>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.logService.findAll());
    }
}
