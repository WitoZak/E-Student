package com.kodilla.studentdatabase.domain;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Grade}
 */
@Value
public class GradeDto implements Serializable {
    Long id;
    String value;
    LocalDateTime gradeTimestamp;
    Subject subject;
    Teacher teacher;
    Student student;
}