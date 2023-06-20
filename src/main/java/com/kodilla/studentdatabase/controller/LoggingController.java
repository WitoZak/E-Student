package com.kodilla.studentdatabase.controller;

import com.kodilla.studentdatabase.domain.Logging;
import com.kodilla.studentdatabase.domain.LoggingDto;
import com.kodilla.studentdatabase.mapper.LoggingMapper;
import com.kodilla.studentdatabase.service.LoggingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/logging")
public class LoggingController {

    private final LoggingService loggingService;
    private final LoggingMapper loggingMapper;


    @GetMapping
    public ResponseEntity<List<LoggingDto>> getAllLoggings() {
        List<Logging> allLoggings = loggingService.getAllLoggings();
        return ResponseEntity.ok(loggingMapper.mapToLoggingDtoList(allLoggings));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoggingDto> getLoggingById(@PathVariable Long id) {
        Logging logging = loggingService.getLoggingById(id);
        LoggingDto loggingDto = loggingMapper.mapToLoggingDto(logging);
        return ResponseEntity.ok(loggingDto);
    }

    @PostMapping
    public ResponseEntity<LoggingDto> createLogging(@RequestBody LoggingDto loggingDto) {
        Logging logging = loggingMapper.mapToLogging(loggingDto);
        Logging createdLogging = loggingService.createLogging(logging);
        LoggingDto createdLoggingDto = loggingMapper.mapToLoggingDto(createdLogging);
        return new ResponseEntity<>(createdLoggingDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoggingDto> updateLogging(@RequestBody LoggingDto loggingDto) {
        Logging logging = loggingMapper.mapToLogging(loggingDto);
        Logging updatedLogging = loggingService.updateLogging(logging);
        LoggingDto updatedLoggingDto = loggingMapper.mapToLoggingDto(updatedLogging);
        return new ResponseEntity<>(updatedLoggingDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogging(@PathVariable Long id) {
        loggingService.deleteLogging(id);
        return ResponseEntity.ok().build();
    }

}
