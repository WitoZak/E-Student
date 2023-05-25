package com.kodilla.studentdatabase.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "SUBJECTS")
public class Subject {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "SUBJECT_NAME")
    private String subjectName;

    @OneToMany(mappedBy = "subject")
    private List<Grade> grades;

    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "TEACHER_ID")
    private Teacher teacher;

}

