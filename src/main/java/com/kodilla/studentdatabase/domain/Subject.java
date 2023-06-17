package com.kodilla.studentdatabase.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "SUBJECTS")
public class Subject {

    @Id
    @GeneratedValue
    @Column(name = "SUBJECT_ID", unique = true)
    private Long id;

    @Column(name = "SUBJECT_NAME")
    private String subjectName;

    @OneToMany(mappedBy = "subject")
    private List<Grade> grades;

    @ManyToOne
    @JoinColumn(name = "TEACHER_ID")
    private Teacher teacher;

}

