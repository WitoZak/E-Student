package com.kodilla.studentdatabase.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "STUDENTS")
public class Student {

    @Id
    @GeneratedValue
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

    @ManyToMany
    @JoinTable(
            name = "STUDENT_SUBJECT",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "SUBJECT_ID")
    )
    private List<Subject> subjects;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @OneToOne(mappedBy = "student")
    private Logging logging;

}