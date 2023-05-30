package com.kodilla.studentdatabase.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Student}
 */
@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class StudentDto implements Serializable {
    Long id;
    Integer logNumber;
    String firstName;
    String lastName;
    String dateOfBirth;
    String address;
    String studentMail;
    String studentPhone;
    List<GradeDto> gradesDtos;
    Long subjectId;
    String subjectName;
    Long groupId;
    String groupName;
}