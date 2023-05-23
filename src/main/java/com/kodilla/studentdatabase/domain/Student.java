package com.kodilla.studentdatabase.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "STUDENTS")
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "LOG_NUMBER")
    private int logNumber;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "DATE_OF_BIRTH")
    private String dateOfBirth;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "STUDENT_MAIL")
    private String studentMail;
    @Column(name = "STUDENT_PHONE")
    private String studentPhone;


}