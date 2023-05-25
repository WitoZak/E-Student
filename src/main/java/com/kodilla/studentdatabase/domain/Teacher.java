package com.kodilla.studentdatabase.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "TEACHERS")
public class Teacher {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @OneToMany(mappedBy = "teacher")
    private List<Subject> subjects;

    @OneToMany(mappedBy = "teacher")
    private List<Grade> grades;

    @OneToOne(mappedBy = "teacher")
    private Logging logging;
}
