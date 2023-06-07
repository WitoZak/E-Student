package com.kodilla.studentdatabase.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Teacher}
 */
@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class TeacherDto implements Serializable {
    Long id;
    String firstName;
    String lastName;
    String mail;
    String phone;
    List<SubjectDto> subjectsDtos;
    List<GradeDto> gradesDtos;
}