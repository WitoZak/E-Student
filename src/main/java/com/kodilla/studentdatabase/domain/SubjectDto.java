package com.kodilla.studentdatabase.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Subject}
 */
@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class SubjectDto implements Serializable {
    Long id;
    String subjectName;
    List<GradeDto> gradesDtos;
    Long teacherId;
}