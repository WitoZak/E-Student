package com.kodilla.studentdatabase.domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Grade}
 */
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class GradeDto implements Serializable {
    Long id;
    String value;
    LocalDateTime gradeTimestamp;
    Long subjectId;
    String subjectName;
    Long teacherId;
    String lastName;
    Long studentId;
}