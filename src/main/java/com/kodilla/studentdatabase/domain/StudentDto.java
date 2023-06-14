package com.kodilla.studentdatabase.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Student}
 */
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class StudentDto implements Serializable {
    Long id;
    Integer logNumber;
    String firstName;
    String lastName;
    String dateOfBirth;
    String address;
    String mail;
    String phone;
    List<GradeDto> gradesDtos;
    Long subjectId;
    String subjectName;
    Long groupId;
    String groupName;
}