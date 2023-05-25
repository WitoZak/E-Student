package com.kodilla.studentdatabase.domain;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Logging}
 */
@Value
public class LoggingDto implements Serializable {
    Long id;
    LocalDateTime logTimestamp;
    Student student;
    Teacher teacher;
}