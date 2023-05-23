package com.kodilla.studentdatabase.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;
    private int logNumber;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String address;
    private String studentMail;
    private String studentPhone;

}
