package com.kodilla.studentdatabase.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    String subjectName;
    String lastName;
    String studentLastName;
}