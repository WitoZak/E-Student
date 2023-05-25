package com.kodilla.studentdatabase.domain;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Teacher}
 */
@Value
public class TeacherDto implements Serializable {
    Long id;
    String firstName;
    String lastName;
    String email;
    String phone;
    List<SubjectDto> subjectsDtos;
    List<GradeDto> gradesDtos;
    LoggingDto logging;
}