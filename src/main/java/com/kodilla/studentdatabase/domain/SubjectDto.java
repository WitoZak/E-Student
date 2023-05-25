package com.kodilla.studentdatabase.domain;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Subject}
 */
@Value
public class SubjectDto implements Serializable {
    Long id;
    String subjectName;
    List<GradeDto> gradesDtos;
    List<StudentDto> studentsDtos;
    Teacher teacher;
}