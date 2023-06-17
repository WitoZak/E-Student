package com.kodilla.studentdatabase.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
    String studentLastName;
    String dateOfBirth;
    String address;
    String mail;
    Integer phone;
    String groupName;
}