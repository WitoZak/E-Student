package com.kodilla.studentdatabase.domain;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Student}
 */
@Value
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
    Long groupId;
}