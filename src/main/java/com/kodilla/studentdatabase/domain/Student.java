package com.kodilla.studentdatabase.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "STUDENTS")
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "STUDENT_ID", unique = true)
    private Long id;

    @Column(name = "LOG_NUMBER")
    private Integer logNumber;

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

    @OneToMany(mappedBy = "student")
    private List<Grade> grades;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_NAME")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "CLASS_NAME")
    private Group group;

    @Builder
    public Student(Long id, Integer logNumber, String firstName, String lastName, String dateOfBirth,
                   String address, String studentMail, String studentPhone, List<Grade> grades, Subject subject,
                   Group group) {
        this.id = id;
        this.logNumber = logNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.studentMail = studentMail;
        this.studentPhone = studentPhone;
        this.grades = grades;
        this.group = group;
    }
}