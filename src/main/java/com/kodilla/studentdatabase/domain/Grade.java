package com.kodilla.studentdatabase.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "GRADES")
public class Grade {
    @Id
    @GeneratedValue
    @Column(name = "GRADES_ID", unique = true)
    private Long id;

    @Column(name = "GRADE_VALUE")
    private String value;

    @Column(name = "GRADING_DATE")
    private LocalDateTime gradeTimestamp;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "TEACHER_ID")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID")
    private Student student;
}
